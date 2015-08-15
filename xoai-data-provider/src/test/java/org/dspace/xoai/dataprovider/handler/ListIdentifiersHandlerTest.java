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
import org.dspace.xoai.dataprovider.request.ListIdentifiersRequest;
import org.dspace.xoai.dataprovider.request.RequestedMetadataPrefix;
import org.dspace.xoai.dataprovider.request.RequestedSet;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.dataprovider.util.support.ErrorOrResponseMatcher;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.ListIdentifiers;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.Collections;

import static org.dspace.xoai.model.oaipmh.ListIdentifiersMatcher.aListIdentifiersThat;
import static org.dspace.xoai.model.oaipmh.OptionalMatchers.isAbsent;
import static org.dspace.xoai.model.oaipmh.OptionalMatchers.value;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListIdentifiersHandlerTest {
    private final ItemRepository itemRepository = mock(ItemRepository.class);
    private final SetRepository setRepository = mock(SetRepository.class);
    private final MetadataFormatRepository metadataFormatRepository = mock(MetadataFormatRepository.class);
    private final ResumptionTokenResolver itemsResumptionTokenResolver = mock(ResumptionTokenResolver.class);

    private final ListIdentifiersHandler underTest = new ListIdentifiersHandler(itemRepository, setRepository, metadataFormatRepository,
            itemsResumptionTokenResolver);

    @Test
    public void handleWhenMetadataPrefixNotFound() throws Exception {
        String prefix = "prefix";

        ListIdentifiersRequest request = mock(ListIdentifiersRequest.class);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(metadataFormatRepository.exists(prefix)).willReturn(false);

        ErrorOrResponse<VerbHandler.Error, ListIdentifiers> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListIdentifiers>anErrorOrResponseThat().hasError(VerbHandler.Error.CANNOT_DISSEMINATE_WITH_FORMAT)));
    }

    @Test
    public void handleSetDefinedAndSetsNotSupported() throws Exception {
        String prefix = "prefix";
        String setSpec = "setSpec";

        ListIdentifiersRequest request = mock(ListIdentifiersRequest.class);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(request.getRequestedSet()).willReturn(Optional.of(new RequestedSet(setSpec)));
        given(metadataFormatRepository.exists(prefix)).willReturn(true);
        given(setRepository.supportSets()).willReturn(false);

        ErrorOrResponse<VerbHandler.Error, ListIdentifiers> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListIdentifiers>anErrorOrResponseThat().hasError(VerbHandler.Error.SETS_NOT_SUPPORTED)));
    }

    @Test
    public void handleDelegatingToItemRepositoryPartialListComplete() throws Exception {
        int offset = 0;
        int total = 0;
        String prefix = "prefix";
        String setSpec = "setSpec";

        ListIdentifiersRequest request = mock(ListIdentifiersRequest.class);
        Header header = mock(Header.class);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(request.getRequestedSet()).willReturn(Optional.of(new RequestedSet(setSpec)));
        given(metadataFormatRepository.exists(prefix)).willReturn(true);
        given(setRepository.supportSets()).willReturn(true);
        given(itemRepository.listIdentifiers(request)).willReturn(PartialList.complete(Collections.singleton(header), total, offset));

        ErrorOrResponse<VerbHandler.Error, ListIdentifiers> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListIdentifiers>anErrorOrResponseThat()
                .hasResponse(aListIdentifiersThat()
                                .hasResumptionToken(isAbsent())
                                .hasHeaders(IsIterableContainingInOrder.contains(header))
                )));
    }

    @Test
    public void handleDelegatingToItemRepositoryPartialListIncomplete() throws Exception {
        int offset = 0;
        int total = 0;
        String prefix = "prefix";
        String setSpec = "setSpec";

        ListIdentifiersRequest request = mock(ListIdentifiersRequest.class);
        Header header = mock(Header.class);
        ResumptionToken resumptionToken = mock(ResumptionToken.class);
        PartialList<Header> itemIdentifiers = PartialList.middleIncomplete(Collections.singleton(header), total, offset);

        given(request.getRequestedMetadataPrefix()).willReturn(new RequestedMetadataPrefix(prefix));
        given(request.getRequestedSet()).willReturn(Optional.of(new RequestedSet(setSpec)));
        given(metadataFormatRepository.exists(prefix)).willReturn(true);
        given(setRepository.supportSets()).willReturn(true);
        given(itemRepository.listIdentifiers(request)).willReturn(itemIdentifiers);
        given(itemsResumptionTokenResolver.resolveForIdentifiers(itemIdentifiers)).willReturn(resumptionToken);

        ErrorOrResponse<VerbHandler.Error, ListIdentifiers> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListIdentifiers>anErrorOrResponseThat()
                .hasResponse(aListIdentifiersThat()
                                .hasResumptionToken(value(equalTo(resumptionToken)))
                                .hasHeaders(IsIterableContainingInOrder.contains(header))
                )));
    }
}