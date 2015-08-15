/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.repository;

import org.dspace.xoai.model.oaipmh.DeletedRecord;
import org.dspace.xoai.model.oaipmh.Granularity;

import java.util.List;

public class RepositoryInformation {
    private final String repositoryName;
    private final List<String> adminEmails;
    private final String baseUrl;
    private final Granularity granularity;
    private final DeletedRecord deleteMethod;
    private final List<String> descriptions;
    private final List<String> compressions;

    public RepositoryInformation(String repositoryName, List<String> adminEmails, String baseUrl, Granularity granularity, DeletedRecord deleteMethod, List<String> descriptions, List<String> compressions) {
        this.repositoryName = repositoryName;
        this.adminEmails = adminEmails;
        this.baseUrl = baseUrl;
        this.granularity = granularity;
        this.deleteMethod = deleteMethod;
        this.descriptions = descriptions;
        this.compressions = compressions;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public List<String> getAdminEmails() {
        return adminEmails;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public DeletedRecord getDeleteMethod() {
        return deleteMethod;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getCompressions() {
        return compressions;
    }
}
