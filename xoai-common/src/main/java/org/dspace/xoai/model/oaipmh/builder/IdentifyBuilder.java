/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import org.dspace.xoai.model.oaipmh.DeletedRecord;
import org.dspace.xoai.model.oaipmh.Description;
import org.dspace.xoai.model.oaipmh.Granularity;
import org.dspace.xoai.model.oaipmh.Identify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class IdentifyBuilder {
    protected String repositoryName;
    protected String baseURL;
    protected String protocolVersion = "1.0";
    protected Date earliestDatestamp;
    protected DeletedRecord deletedRecord = DeletedRecord.NO;
    protected Granularity granularity = Granularity.Second;
    protected final List<String> adminEmails = new ArrayList<String>();
    protected final List<String> compressions = new ArrayList<String>();
    protected final List<Description> descriptions = new ArrayList<Description>();

    private IdentifyBuilder() {
    }

    public static IdentifyBuilder anIdentify() {
        return new IdentifyBuilder();
    }

    public IdentifyBuilder withRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
        return this;
    }

    public IdentifyBuilder withBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public IdentifyBuilder withProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
        return this;
    }

    public IdentifyBuilder withAdminEmails(List<String> adminEmails) {
        this.adminEmails.addAll(adminEmails);
        return this;
    }

    public IdentifyBuilder withAdminEmail(String adminEmails) {
        this.adminEmails.add(adminEmails);
        return this;
    }

    public IdentifyBuilder withEarliestDatestamp(Date earliestDatestamp) {
        this.earliestDatestamp = earliestDatestamp;
        return this;
    }

    public IdentifyBuilder withDeletedRecord(DeletedRecord deletedRecord) {
        this.deletedRecord = deletedRecord;
        return this;
    }

    public IdentifyBuilder withGranularity(Granularity granularity) {
        this.granularity = granularity;
        return this;
    }

    public IdentifyBuilder withCompressions(List<String> compressions) {
        this.compressions.addAll(compressions);
        return this;
    }

    public IdentifyBuilder withCompression(String compressions) {
        this.compressions.add(compressions);
        return this;
    }

    public IdentifyBuilder withDescriptions(Collection<Description> descriptions) {
        this.descriptions.addAll(descriptions);
        return this;
    }

    public IdentifyBuilder withDescription(Description descriptions) {
        this.descriptions.add(descriptions);
        return this;
    }

    public IdentifyBuilder but() {
        return anIdentify().withRepositoryName(repositoryName).withBaseURL(baseURL).withProtocolVersion(protocolVersion).withAdminEmails(adminEmails).withEarliestDatestamp(earliestDatestamp).withDeletedRecord(deletedRecord).withGranularity(granularity).withCompressions(compressions).withDescriptions(descriptions);
    }

    public Identify build() {
        Identify identify = new Identify(repositoryName, baseURL, protocolVersion, adminEmails, earliestDatestamp, deletedRecord, granularity, compressions, descriptions);
        return identify;
    }


}
