/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import java.util.Date;
import java.util.List;

public class Identify implements OAIPMHContent {
    private final String repositoryName;
    private final String baseURL;
    private final String protocolVersion;
    private final List<String> adminEmails;
    private final Date earliestDatestamp;
    private final DeletedRecord deletedRecord;
    private final Granularity granularity;
    private final List<String> compressions;
    private final List<Description> descriptions;

    public Identify(String repositoryName, String baseURL, String protocolVersion, List<String> adminEmails, Date earliestDatestamp, DeletedRecord deletedRecord, Granularity granularity, List<String> compressions, List<Description> descriptions) {
        this.repositoryName = repositoryName;
        this.baseURL = baseURL;
        this.protocolVersion = protocolVersion;
        this.adminEmails = adminEmails;
        this.earliestDatestamp = earliestDatestamp;
        this.deletedRecord = deletedRecord;
        this.granularity = granularity;
        this.compressions = compressions;
        this.descriptions = descriptions;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public List<String> getAdminEmails() {
        return this.adminEmails;
    }

    public Date getEarliestDatestamp() {
        return earliestDatestamp;
    }

    public DeletedRecord getDeletedRecord() {
        return deletedRecord;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public List<String> getCompressions() {
        return this.compressions;
    }

    public List<Description> getDescriptions() {
        return this.descriptions;
    }

//    @Override
//    public void write(XmlDataProviderWriter writer) throws XmlWriteException {
//        if (this.repositoryName == null) throw new XmlWriteException("Repository Name cannot be null");
//        if (this.baseURL == null) throw new XmlWriteException("Base URL cannot be null");
//        if (this.protocolVersion == null) throw new XmlWriteException("Protocol version cannot be null");
//        if (this.earliestDatestamp == null) throw new XmlWriteException("Eerliest datestamp cannot be null");
//        if (this.deletedRecord == null) throw new XmlWriteException("Deleted record persistency cannot be null");
//        if (this.granularity == null) throw new XmlWriteException("Granularity cannot be null");
//        if (this.adminEmails == null || this.adminEmails.isEmpty())
//            throw new XmlWriteException("List of admin emails cannot be null or empty");
//
//        writer.writeElement("repositoryName", repositoryName);
//        writer.writeElement("baseURL", baseURL);
//        writer.writeElement("protocolVersion", protocolVersion);
//
//        for (String email : this.adminEmails)
//            writer.writeElement("adminEmail", email);
//
//        writer.writeElement("earliestDatestamp", earliestDatestamp, Granularity.Second);
//        writer.writeElement("deletedRecord", deletedRecord.value());
//        writer.writeElement("granularity", granularity.toString());
//
//        if (!this.compressions.isEmpty())
//            for (String compression : this.compressions)
//                writer.writeElement("compression", compression);
//
//        if (!this.descriptions.isEmpty())
//            for (Description description : this.descriptions)
//                writer.writeElement("description", description);
//    }
}
