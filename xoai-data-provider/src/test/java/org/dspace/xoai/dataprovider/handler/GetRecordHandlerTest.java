/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import org.dspace.xoai.dataprovider.repository.ItemRepository;
import org.dspace.xoai.dataprovider.request.GetRecordRequest;
import org.dspace.xoai.dataprovider.request.RequestedIdentifier;
import org.dspace.xoai.dataprovider.request.RequestedMetadataPrefix;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.support.ErrorOrResponseMatcher;
import org.dspace.xoai.model.oaipmh.GetRecord;
import org.dspace.xoai.model.oaipmh.GetRecordMatcher;
import org.dspace.xoai.model.oaipmh.Record;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetRecordHandlerTest {
    private final ItemRepository itemResolver = mock(ItemRepository.class);

    private final GetRecordHandler underTest = new GetRecordHandler(itemResolver);

    @Test
    public void handleWhenItemNotFound() throws Exception {
        // given
        String identifier = "identifier";
        String prefix = "prefix";
        GetRecordRequest request = request(identifier, prefix);
        ErrorOrResponse<ItemRepository.Error, Record> itemResult = ErrorOrResponse.error(ItemRepository.Error.ID_DOES_NOT_EXIST);

        given(itemResolver.resolve(identifier, prefix)).willReturn(itemResult);

        // when
        ErrorOrResponse<VerbHandler.Error, GetRecord> result = underTest.handle(request);

        // then
        assertThat(result, is(
                ErrorOrResponseMatcher.<VerbHandler.Error, GetRecord>anErrorOrResponseThat()
                        .hasError(VerbHandler.Error.ITEM_DOES_NOT_EXIST)
        ));
    }

    @Test
    public void handleWhenMetadataNotExists() throws Exception {
        // given
        String identifier = "identifier";
        String prefix = "prefix";
        GetRecordRequest request = request(identifier, prefix);
        ErrorOrResponse<ItemRepository.Error, Record> itemResult = ErrorOrResponse.error(ItemRepository.Error.CANNOT_DISSEMINATE_FORMAT);

        given(itemResolver.resolve(identifier, prefix)).willReturn(itemResult);

        // when
        ErrorOrResponse<VerbHandler.Error, GetRecord> result = underTest.handle(request);

        // then
        assertThat(result, is(
                ErrorOrResponseMatcher.<VerbHandler.Error, GetRecord>anErrorOrResponseThat()
                        .hasError(VerbHandler.Error.CANNOT_DISSEMINATE_WITH_FORMAT)
        ));
    }

    @Test
    public void handleHappy() throws Exception {
        // given
        String identifier = "identifier";
        String prefix = "prefix";
        Record record = mock(Record.class);
        GetRecordRequest request = request(identifier, prefix);
        ErrorOrResponse<ItemRepository.Error, Record> itemResult = ErrorOrResponse.response(record);
        given(itemResolver.resolve(identifier, prefix)).willReturn(itemResult);

        // when
        ErrorOrResponse<VerbHandler.Error, GetRecord> result = underTest.handle(request);

        // then
        assertThat(result, is(
                ErrorOrResponseMatcher.<VerbHandler.Error, GetRecord>anErrorOrResponseThat()
                        .hasResponse(GetRecordMatcher.aGetRecordThat().hasRecord(record))
        ));
    }

    private GetRecordRequest request(String identifier, String prefix) {
        return new GetRecordRequest(new RequestedIdentifier(identifier), new RequestedMetadataPrefix(prefix));
    }
}