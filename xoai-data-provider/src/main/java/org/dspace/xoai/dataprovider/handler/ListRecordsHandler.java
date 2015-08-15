/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import org.dspace.xoai.dataprovider.repository.ItemRepository;
import org.dspace.xoai.dataprovider.repository.MetadataFormatRepository;
import org.dspace.xoai.dataprovider.repository.ResumptionTokenResolver;
import org.dspace.xoai.dataprovider.repository.SetRepository;
import org.dspace.xoai.dataprovider.request.ListRecordsRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.model.oaipmh.ListRecords;
import org.dspace.xoai.model.oaipmh.Record;
import org.dspace.xoai.model.oaipmh.builder.ListRecordsBuilder;

public class ListRecordsHandler implements VerbHandler<ListRecordsRequest, ListRecords> {
    private final ItemRepository itemRepository;
    private final SetRepository setRepository;
    private final MetadataFormatRepository metadataFormatRepository;
    private final ResumptionTokenResolver itemsResumptionTokenResolver;

    public ListRecordsHandler(ItemRepository itemRepository, SetRepository setRepository, MetadataFormatRepository metadataFormatRepository, ResumptionTokenResolver itemsResumptionTokenResolver) {
        this.itemRepository = itemRepository;
        this.setRepository = setRepository;
        this.metadataFormatRepository = metadataFormatRepository;
        this.itemsResumptionTokenResolver = itemsResumptionTokenResolver;
    }

    @Override
    public ErrorOrResponse<Error, ListRecords> handle(ListRecordsRequest request) {
        if (metadataFormatRepository.exists(request.getRequestedMetadataPrefix().getValue())) {
            if (request.getRequestedSet().isPresent() && !setRepository.supportSets()) {
                return ErrorOrResponse.error(VerbHandler.Error.SETS_NOT_SUPPORTED);
            } else {
                PartialList<Record> records = itemRepository.listRecords(request);

                ListRecordsBuilder listRecordsBuilder = ListRecordsBuilder.aListRecords()
                        .withRecords(records.getItems());

                if (records.getType() == PartialList.Type.INCOMPLETE) {
                    listRecordsBuilder.withResumptionToken(itemsResumptionTokenResolver.resolveForRecords(records));
                }

                return ErrorOrResponse.response(listRecordsBuilder.build());
            }
        } else {
            return ErrorOrResponse.error(VerbHandler.Error.CANNOT_DISSEMINATE_WITH_FORMAT);
        }
    }
}
