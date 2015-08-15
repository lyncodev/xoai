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
import org.dspace.xoai.dataprovider.util.support.ErrorOrResponseMatcher;
import org.dspace.xoai.model.oaipmh.ListSets;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.dspace.xoai.model.oaipmh.Set;
import org.junit.Test;

import static java.util.Collections.singleton;
import static org.dspace.xoai.dataprovider.util.PartialList.complete;
import static org.dspace.xoai.dataprovider.util.PartialList.middleIncomplete;
import static org.dspace.xoai.model.oaipmh.ListSetsMatcher.aListSetsThat;
import static org.dspace.xoai.model.oaipmh.OptionalMatchers.isAbsent;
import static org.dspace.xoai.model.oaipmh.OptionalMatchers.value;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListSetsHandlerTest {
    public static final int SETS_PER_PAGE = 100;
    private final SetRepository setRepository = mock(SetRepository.class);
    private final ResumptionTokenResolver setResumptionTokenResolver = mock(ResumptionTokenResolver.class);
    private final ListSetsHandler underTest = new ListSetsHandler(SETS_PER_PAGE, setRepository, setResumptionTokenResolver);

    @Test
    public void handleIfSetsUnsupported() throws Exception {
        int offset = 100;
        ListSetsRequest listSetsRequest = new ListSetsRequest(offset);
        given(setRepository.supportSets()).willReturn(false);

        ErrorOrResponse<VerbHandler.Error, ListSets> result = underTest.handle(listSetsRequest);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListSets>anErrorOrResponseThat().hasError(VerbHandler.Error.SETS_NOT_SUPPORTED)));
    }

    @Test
    public void handleIfSetsSupportedAndPartialListComplete() throws Exception {
        int offset = 100;
        String setSpec = "setSpec";
        String setName = "setName";
        ListSetsRequest listSetsRequest = new ListSetsRequest(offset);


        Set set = mock(Set.class);

        given(setRepository.supportSets()).willReturn(true);
        given(setRepository.retrieveSets(offset, SETS_PER_PAGE)).willReturn(complete(singleton(set), 10, 0));

        ErrorOrResponse<VerbHandler.Error, ListSets> result = underTest.handle(listSetsRequest);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListSets>anErrorOrResponseThat().hasResponse(aListSetsThat()
                .hasSets(contains(set))
                .hasResumptionToken(isAbsent()))));
    }

    @Test
    public void handleIfSetsSupportedAndPartialListIncompleteMiddle() throws Exception {
        int total = 110;
        int offset = 100;
        ListSetsRequest listSetsRequest = new ListSetsRequest(offset);

        Set set = mock(Set.class);
        ResumptionToken resumptionToken = mock(ResumptionToken.class);

        PartialList<Set> oaiSetPartialList = middleIncomplete(singleton(set), total, offset);

        given(setRepository.supportSets()).willReturn(true);
        given(setRepository.retrieveSets(offset, SETS_PER_PAGE)).willReturn(oaiSetPartialList);
        given(setResumptionTokenResolver.resolveForSets(oaiSetPartialList)).willReturn(resumptionToken);

        ErrorOrResponse<VerbHandler.Error, ListSets> result = underTest.handle(listSetsRequest);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, ListSets>anErrorOrResponseThat().hasResponse(aListSetsThat()
                .hasSets(contains(set))
                .hasResumptionToken(value(equalTo(resumptionToken))))));
    }
}