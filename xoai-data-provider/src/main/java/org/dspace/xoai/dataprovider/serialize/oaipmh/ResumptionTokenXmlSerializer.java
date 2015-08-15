/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.date.DateTimeFormatter;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.ResumptionToken;

public class ResumptionTokenXmlSerializer implements XmlSerializer<ResumptionToken> {
    private final DateTimeFormatter dateTimeFormatter;

    public ResumptionTokenXmlSerializer(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, ResumptionToken input) {
        xmlWriter.writeStartElement("resumptionToken");
        if (input.getExpirationDate().isPresent()) {
            xmlWriter.writeAttribute("expirationDate", dateTimeFormatter.apply(input.getExpirationDate().get()));
        }

        if (input.getCompleteListSize().isPresent()) {
            xmlWriter.writeAttribute("completeListSize", String.valueOf(input.getCompleteListSize().get()));
        }

        if (input.getCursor().isPresent()) {
            xmlWriter.writeAttribute("cursor", String.valueOf(input.getCursor().get()));
        }

        if (input.getValue().isPresent()) {
            xmlWriter.writeCharacters(input.getValue().get());
        }
        xmlWriter.writeEndElement();
    }
}
