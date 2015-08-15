/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import org.dspace.xoai.dataprovider.repository.EarliestDateResolver;
import org.dspace.xoai.dataprovider.repository.RepositoryInformation;
import org.dspace.xoai.dataprovider.request.IdentifyRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.support.ErrorOrResponseMatcher;
import org.dspace.xoai.model.oaipmh.Identify;
import org.junit.Test;

import java.util.Date;

import static org.dspace.xoai.model.oaipmh.IdentifyMatcher.anIdentifyThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class IdentifyHandlerTest {
    private final EarliestDateResolver earliestDateResolver = mock(EarliestDateResolver.class);
    private final RepositoryInformation repositoryInformation = mock(RepositoryInformation.class);
    private IdentifyHandler underTest = new IdentifyHandler(repositoryInformation, earliestDateResolver);

    @Test
    public void handle() throws Exception {
        IdentifyRequest request = new IdentifyRequest();
        String baseUrl = "baseUrl";
        Date earliestDate = new Date();

        given(repositoryInformation.getBaseUrl()).willReturn(baseUrl);
        given(earliestDateResolver.resolve()).willReturn(earliestDate);

        ErrorOrResponse<VerbHandler.Error, Identify> result = underTest.handle(request);

        assertThat(result, is(ErrorOrResponseMatcher.<VerbHandler.Error, Identify>anErrorOrResponseThat()
                .hasResponse(anIdentifyThat()
                                .hasBaseURL(equalTo(baseUrl))
                                .hasEarliestDatestamp(equalTo(earliestDate))
                )

        ));
    }
}