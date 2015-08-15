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
import org.dspace.xoai.dataprovider.request.ListMetadataFormatsRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.model.oaipmh.ListMetadataFormats;
import org.dspace.xoai.model.oaipmh.MetadataFormat;
import org.dspace.xoai.model.oaipmh.builder.ListMetadataFormatsBuilder;

import java.util.Collection;

public class ListMetadataFormatsHandler implements VerbHandler<ListMetadataFormatsRequest, ListMetadataFormats> {
    private final ItemRepository itemResolver;
    private final MetadataFormatRepository metadataFormatRepository;

    public ListMetadataFormatsHandler(ItemRepository itemResolver, MetadataFormatRepository metadataFormatRepository) {
        this.itemResolver = itemResolver;
        this.metadataFormatRepository = metadataFormatRepository;
    }

    @Override
    public ErrorOrResponse<Error, ListMetadataFormats> handle(ListMetadataFormatsRequest request) {
        if (request.getIdentifier().isPresent()) {
            String identifier = request.getIdentifier().get().getValue();
            boolean exists = itemResolver.exists(identifier);
            if (exists) {
                Collection<MetadataFormat> metadataFormats = metadataFormatRepository.getMetadataFormatsFor(identifier);

                if (metadataFormats.isEmpty()) {
                    return ErrorOrResponse.error(VerbHandler.Error.NO_METADATA_FORMATS);
                } else {
                    return ErrorOrResponse.response(ListMetadataFormatsBuilder.aListMetadataFormats()
                            .withMetadataFormats(metadataFormats)
                            .build());
                }
            } else {
                return ErrorOrResponse.error(VerbHandler.Error.ITEM_DOES_NOT_EXIST);
            }
        } else {
            Collection<MetadataFormat> metadataFormats = metadataFormatRepository.getMetadataFormats();

            if (metadataFormats.isEmpty()) {
                return ErrorOrResponse.error(VerbHandler.Error.NO_METADATA_FORMATS);
            } else {
                return ErrorOrResponse.response(ListMetadataFormatsBuilder.aListMetadataFormats()
                        .withMetadataFormats(metadataFormats)
                        .build());
            }
        }
    }
}
