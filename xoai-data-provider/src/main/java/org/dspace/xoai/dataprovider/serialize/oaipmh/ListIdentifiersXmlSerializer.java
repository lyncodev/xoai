/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.ListIdentifiers;

public class ListIdentifiersXmlSerializer implements XmlSerializer<ListIdentifiers> {
    private final HeaderXmlSerializer headerXmlSerializer;
    private final ResumptionTokenXmlSerializer resumptionTokenXmlSerializer;

    public ListIdentifiersXmlSerializer(HeaderXmlSerializer headerXmlSerializer, ResumptionTokenXmlSerializer resumptionTokenXmlSerializer) {
        this.headerXmlSerializer = headerXmlSerializer;
        this.resumptionTokenXmlSerializer = resumptionTokenXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, ListIdentifiers input) {
        xmlWriter.writeStartElement("ListIdentifiers");
        for (Header header : input.getHeaders()) {
            headerXmlSerializer.serialize(xmlWriter, header);
        }

        if (input.getResumptionToken().isPresent()) {
            resumptionTokenXmlSerializer.serialize(xmlWriter, input.getResumptionToken().get());
        }

        xmlWriter.writeEndElement();
    }
}
