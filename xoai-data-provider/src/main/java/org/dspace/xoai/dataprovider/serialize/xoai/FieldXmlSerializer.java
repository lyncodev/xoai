/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.xoai;

import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.xoai.Field;

public class FieldXmlSerializer implements XmlSerializer<Field> {
    private static final String FIELD = "field";
    private static final String NAME = "name";

    @Override
    public void serialize(XmlWriter xmlWriter, Field input) {
        xmlWriter.writeStartElement(FIELD);
        if (input.getName().isPresent()) {
            xmlWriter.writeAttribute(NAME, input.getName().get());
        }
        xmlWriter.writeCharacters(input.getValue());
        xmlWriter.writeEndElement();
    }
}
