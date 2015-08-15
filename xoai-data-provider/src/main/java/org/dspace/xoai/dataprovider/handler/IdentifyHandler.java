/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.dspace.xoai.dataprovider.repository.EarliestDateResolver;
import org.dspace.xoai.dataprovider.repository.RepositoryInformation;
import org.dspace.xoai.dataprovider.request.IdentifyRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.model.oaipmh.Description;
import org.dspace.xoai.model.oaipmh.Identify;
import org.dspace.xoai.model.oaipmh.builder.IdentifyBuilder;

public class IdentifyHandler implements VerbHandler<IdentifyRequest, Identify> {
    public static final String PROTOCOL_VERSION = "2.0";

    private final RepositoryInformation repositoryInformation;
    private final EarliestDateResolver earliestDateResolver;

    public IdentifyHandler(RepositoryInformation repositoryInformation, EarliestDateResolver earliestDateResolver) {
        this.repositoryInformation = repositoryInformation;
        this.earliestDateResolver = earliestDateResolver;
    }

    @Override
    public ErrorOrResponse<Error, Identify> handle(IdentifyRequest request) {
        return ErrorOrResponse.response(IdentifyBuilder.anIdentify()
                .withRepositoryName(repositoryInformation.getRepositoryName())
                .withBaseURL(repositoryInformation.getBaseUrl())
                .withGranularity(repositoryInformation.getGranularity())
                .withEarliestDatestamp(earliestDateResolver.resolve())
                .withAdminEmails(repositoryInformation.getAdminEmails())
                .withCompressions(repositoryInformation.getCompressions())
                .withDeletedRecord(repositoryInformation.getDeleteMethod())
                .withDescriptions(Collections2.transform(repositoryInformation.getDescriptions(), toDescription()))
                .withProtocolVersion(PROTOCOL_VERSION)
                .build());
    }

    private Function<? super String, Description> toDescription() {
        return new Function<String, Description>() {
            @Override
            public Description apply(String compiledMetadata) {
                return new Description(compiledMetadata);
            }
        };
    }
}
