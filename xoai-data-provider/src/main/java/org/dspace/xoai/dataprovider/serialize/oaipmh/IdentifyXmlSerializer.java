/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.date.OAIDateFormatter;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Description;
import org.dspace.xoai.model.oaipmh.Identify;

public class IdentifyXmlSerializer implements XmlSerializer<Identify> {
    private final OAIDateFormatter oaiDateFormatter;
    private final DescriptionXmlSerializer descriptionXmlSerializer;

    public IdentifyXmlSerializer(OAIDateFormatter oaiDateFormatter, DescriptionXmlSerializer descriptionXmlSerializer) {
        this.oaiDateFormatter = oaiDateFormatter;
        this.descriptionXmlSerializer = descriptionXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter writer, Identify input) {
        writer.writeStartElement("Identify");

        writer.writeElement("repositoryName", input.getRepositoryName());
        writer.writeElement("baseURL", input.getBaseURL());
        writer.writeElement("protocolVersion", input.getProtocolVersion());


        for (String email : input.getAdminEmails()) {
            writer.writeElement("adminEmail", email);
        }

        writer.writeElement("earliestDatestamp", oaiDateFormatter.apply(input.getEarliestDatestamp()));
        writer.writeElement("deletedRecord", input.getDeletedRecord().value());
        writer.writeElement("granularity", input.getGranularity().toString());


        if (!input.getCompressions().isEmpty()) {
            for (String compression : input.getCompressions()) {
                writer.writeElement("compression", compression);
            }
        }

        if (!input.getDescriptions().isEmpty()) {
            for (Description description : input.getDescriptions()) {
                writer.writeStartElement("description");
                descriptionXmlSerializer.serialize(writer, description);
                writer.writeEndElement();
            }
        }

        writer.writeEndElement();
    }
}
