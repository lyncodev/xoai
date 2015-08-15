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
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.model.oaipmh.GetRecord;
import org.dspace.xoai.model.oaipmh.Record;

public class GetRecordHandler implements VerbHandler<GetRecordRequest, GetRecord> {
    private final ItemRepository itemResolver;

    public GetRecordHandler(ItemRepository itemResolver) {
        this.itemResolver = itemResolver;
    }

    @Override
    public ErrorOrResponse<Error, GetRecord> handle(GetRecordRequest request) {
        String metadataPrefix = request.getRequestedMetadataPrefix().getValue();
        String identifier = request.getRequestedIdentifier().getValue();
        ErrorOrResponse<ItemRepository.Error, Record> result = itemResolver.resolve(identifier, metadataPrefix);
        if (result.isOk()) {
            return ErrorOrResponse.response(new GetRecord(result.getResponse()));
        } else {
            if (result.getError() == ItemRepository.Error.CANNOT_DISSEMINATE_FORMAT) {
                return ErrorOrResponse.error(VerbHandler.Error.CANNOT_DISSEMINATE_WITH_FORMAT);
            } else {
                return ErrorOrResponse.error(Error.ITEM_DOES_NOT_EXIST);
            }
        }
    }
}
