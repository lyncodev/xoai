/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.repository.DateTimeFormatter;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Header;

public class HeaderXmlSerializer implements XmlSerializer<Header> {
    private static final String HEADER = "header";
    private static final String STATUS = "status";
    private static final String IDENTIFIER = "identifier";
    private static final String DATESTAMP = "datestamp";
    private static final String SET_SPEC = "setSpec";

    private final DateTimeFormatter DateTimeFormatter;

    public HeaderXmlSerializer(DateTimeFormatter DateTimeFormatter) {
        this.DateTimeFormatter = DateTimeFormatter;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, Header input) {
        xmlWriter.writeStartElement(HEADER);

        if (input.getStatus().isPresent()) {
            String statusValue = input.getStatus().get().value();
            xmlWriter.writeAttribute(STATUS, statusValue);
        }

        xmlWriter.writeElement(IDENTIFIER, input.getIdentifier());
        xmlWriter.writeElement(DATESTAMP, DateTimeFormatter.format(input.getDatestamp()));

        for (String setSpec : input.getSetSpecs()) {
            xmlWriter.writeElement(SET_SPEC, setSpec);
        }

        xmlWriter.writeEndElement();
    }
}
