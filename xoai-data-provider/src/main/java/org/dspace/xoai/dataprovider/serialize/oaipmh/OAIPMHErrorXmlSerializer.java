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
import org.dspace.xoai.model.oaipmh.OAIPMHError;

public class OAIPMHErrorXmlSerializer implements XmlSerializer<OAIPMHError> {
    private static final String ERROR = "error";
    private static final String CODE = "code";

    @Override
    public void serialize(XmlWriter xmlWriter, OAIPMHError input) {
        xmlWriter.writeStartElement(ERROR);

        if (input.getCode().isPresent()) {
            xmlWriter.writeAttribute(CODE, input.getCode().get().toString());
        }

        xmlWriter.writeCharacters(input.getMessage());
        xmlWriter.writeEndElement();
    }
}
