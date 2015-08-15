/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import org.dspace.xoai.dataprovider.repository.ResumptionTokenResolver;
import org.dspace.xoai.dataprovider.repository.SetRepository;
import org.dspace.xoai.dataprovider.request.ListSetsRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.model.oaipmh.ListSets;
import org.dspace.xoai.model.oaipmh.Set;
import org.dspace.xoai.model.oaipmh.builder.ListSetsBuilder;

public class ListSetsHandler implements VerbHandler<ListSetsRequest, ListSets> {
    private final int setsPerPage;
    private final SetRepository setRepository;
    private final ResumptionTokenResolver resumptionTokenResolver;

    public ListSetsHandler(int setsPerPage, SetRepository setRepository, ResumptionTokenResolver resumptionTokenResolver) {
        this.setsPerPage = setsPerPage;
        this.setRepository = setRepository;
        this.resumptionTokenResolver = resumptionTokenResolver;
    }

    @Override
    public ErrorOrResponse<Error, ListSets> handle(ListSetsRequest request) {
        if (setRepository.supportSets()) {
            PartialList<Set> sets = setRepository.retrieveSets(request.getOffset(), setsPerPage);

            ListSetsBuilder listSetsBuilder = ListSetsBuilder.aListSets()
                    .withSets(sets.getItems());

            if (sets.getType() == PartialList.Type.INCOMPLETE) {
                listSetsBuilder.withResumptionToken(resumptionTokenResolver.resolveForSets(sets));
            }

            return ErrorOrResponse.response(listSetsBuilder.build());
        } else {
            return ErrorOrResponse.error(VerbHandler.Error.SETS_NOT_SUPPORTED);
        }
    }

}
