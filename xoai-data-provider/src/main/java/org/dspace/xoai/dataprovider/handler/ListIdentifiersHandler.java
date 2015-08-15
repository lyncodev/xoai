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
import org.dspace.xoai.dataprovider.request.ListIdentifiersRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.ListIdentifiers;
import org.dspace.xoai.model.oaipmh.builder.ListIdentifiersBuilder;

public class ListIdentifiersHandler implements VerbHandler<ListIdentifiersRequest, ListIdentifiers> {
    private final ItemRepository itemRepository;
    private final SetRepository setRepository;
    private final MetadataFormatRepository metadataFormatRepository;
    private final ResumptionTokenResolver itemsResumptionTokenResolver;

    public ListIdentifiersHandler(ItemRepository itemRepository, SetRepository setRepository, MetadataFormatRepository metadataFormatRepository, ResumptionTokenResolver itemsResumptionTokenResolver) {
        this.itemRepository = itemRepository;
        this.setRepository = setRepository;
        this.metadataFormatRepository = metadataFormatRepository;
        this.itemsResumptionTokenResolver = itemsResumptionTokenResolver;
    }

    @Override
    public ErrorOrResponse<Error, ListIdentifiers> handle(ListIdentifiersRequest request) {
        if (metadataFormatRepository.exists(request.getRequestedMetadataPrefix().getValue())) {
            if (request.getRequestedSet().isPresent() && !setRepository.supportSets()) {
                return ErrorOrResponse.error(VerbHandler.Error.SETS_NOT_SUPPORTED);
            } else {
                PartialList<Header> itemIdentifiers = itemRepository.listIdentifiers(request);

                ListIdentifiersBuilder listIdentifiersBuilder = ListIdentifiersBuilder.aListIdentifiers()
                        .withHeaders(itemIdentifiers.getItems());

                if (itemIdentifiers.getType() == PartialList.Type.INCOMPLETE) {
                    listIdentifiersBuilder.withResumptionToken(itemsResumptionTokenResolver.resolveForIdentifiers(itemIdentifiers));
                }

                return ErrorOrResponse.response(listIdentifiersBuilder.build());
            }
        } else {
            return ErrorOrResponse.error(VerbHandler.Error.CANNOT_DISSEMINATE_WITH_FORMAT);
        }
    }
}
