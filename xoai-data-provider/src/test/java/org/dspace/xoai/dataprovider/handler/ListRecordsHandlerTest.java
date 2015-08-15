/*
 * The contents of this file are subject to the license and copyright 
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.repository.ItemRepository;
import org.dspace.xoai.dataprovider.repository.MetadataFormatRepository;
import org.dspace.xoai.dataprovider.repository.ResumptionTokenResolver;
import org.dspace.xoai.dataprovider.repository.SetRepository;
import org.dspace.xoai.dataprovider.request.ListRecordsRequest;
import org.dspace.xoai.dataprovider.request.RequestedMetadataPrefix;
import org.dspace.xoai.dataprovider.request.RequestedSet;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.dataprovider.util.support.ErrorOrResponseMatcher;
import org.dspace.xoai.model.oaipmh.ListRecords;
import org.dspace.xoai.model.oaipmh.Record;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.Collections;

import static org.dspace.xoai.model.oaipmh.ListRecordsMatcher.aListRecordsThat;
import static org.dspace.xoai.model.oaipmh.OptionalMatchers.isAbsent;
import static org.dspace.xoai.model.oaipmh.OptionalMatchers.value;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListRecordsHandlerTest {
    private final ItemRepository itemRepository = mock(ItemRepository.class);
    private final SetRepository setRepository = mock(SetRepository.class);
    private final MetadataFormatRepository metadataFormatRepository = mock(MetadataFormatRepository.class);
    private final ResumptionTokenResolver itemsResumptionTokenResolver = mock(ResumptionTokenResolver.class);

    private final ListRecordsHandler underTest = new ListRecordsHandler(itemRepository, setRepository, metadataFormatRepository,
            itemsResumptionTokenResolver);

    @Test
    public void handleWhenMetadataPrefixNotFound() throws Exception {
        String prefix = "prefix";

        ListRecordsRequest request = mock(ListRecordsRequest.class);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(metadataFormatRepository.exists(prefix)).willReturn(false);

        ErrorOrResponse<VerbHandler.Error, ListRecords> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListRecords>anErrorOrResponseThat().hasError(VerbHandler.Error.CANNOT_DISSEMINATE_WITH_FORMAT)));
    }

    @Test
    public void handleSetDefinedAndSetsNotSupported() throws Exception {
        String prefix = "prefix";
        String setSpec = "setSpec";

        ListRecordsRequest request = mock(ListRecordsRequest.class);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(request.getRequestedSet()).willReturn(Optional.of(new RequestedSet(setSpec)));
        given(metadataFormatRepository.exists(prefix)).willReturn(true);
        given(setRepository.supportSets()).willReturn(false);

        ErrorOrResponse<VerbHandler.Error, ListRecords> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListRecords>anErrorOrResponseThat().hasError(VerbHandler.Error.SETS_NOT_SUPPORTED)));
    }

    @Test
    public void handleDelegatingToItemRepositoryPartialListComplete() throws Exception {
        int offset = 0;
        int total = 0;
        String prefix = "prefix";
        String setSpec = "setSpec";

        ListRecordsRequest request = mock(ListRecordsRequest.class);
        Record record = mock(Record.class);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(request.getRequestedSet()).willReturn(Optional.of(new RequestedSet(setSpec)));
        given(metadataFormatRepository.exists(prefix)).willReturn(true);
        given(setRepository.supportSets()).willReturn(true);
        given(itemRepository.listRecords(request)).willReturn(PartialList.complete(Collections.singleton(record), total, offset));

        ErrorOrResponse<VerbHandler.Error, ListRecords> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListRecords>anErrorOrResponseThat()
                .hasResponse(aListRecordsThat()
                                .hasResumptionToken(isAbsent())
                                .hasRecords(IsIterableContainingInOrder.contains(record))
                )));
    }

    @Test
    public void handleDelegatingToItemRepositoryPartialListIncomplete() throws Exception {
        int offset = 0;
        int total = 0;
        String prefix = "prefix";
        String setSpec = "setSpec";

        ListRecordsRequest request = mock(ListRecordsRequest.class);
        Record record = mock(Record.class);
        ResumptionToken resumptionToken = mock(ResumptionToken.class);
        PartialList<Record> records = PartialList.middleIncomplete(Collections.singleton(record), total, offset);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(request.getRequestedSet()).willReturn(Optional.of(new RequestedSet(setSpec)));
        given(metadataFormatRepository.exists(prefix)).willReturn(true);
        given(setRepository.supportSets()).willReturn(true);
        given(itemRepository.listRecords(request)).willReturn(records);
        given(itemsResumptionTokenResolver.resolveForRecords(records)).willReturn(resumptionToken);

        ErrorOrResponse<VerbHandler.Error, ListRecords> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListRecords>anErrorOrResponseThat()
                .hasResponse(aListRecordsThat()
                                .hasResumptionToken(value(equalTo(resumptionToken)))
                                .hasRecords(IsIterableContainingInOrder.contains(record))
                )));
    }
}