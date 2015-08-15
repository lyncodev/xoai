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
import org.dspace.xoai.model.xoai.Element;
import org.dspace.xoai.model.xoai.Field;

import static org.dspace.xoai.dataprovider.serialize.xoai.XOAIMetadataXmlSerializer.XOAI_NAMESPACE_URI;

public class ElementXmlSerializer implements XmlSerializer<Element> {
    private static final String ELEMENT = "element";
    private static final String NAME = "name";
    private final FieldXmlSerializer fieldXmlSerializer;

    public ElementXmlSerializer(FieldXmlSerializer fieldXmlSerializer) {
        this.fieldXmlSerializer = fieldXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter writer, Element input) {
        writer.writeStartElement(XOAI_NAMESPACE_URI, ELEMENT);
        writer.writeAttribute(NAME, input.getName());

        for (Field field : input.getFields()) {
            fieldXmlSerializer.serialize(writer, field);
        }

        for (Element element : input.getElements()) {
            serialize(writer, element);
        }

        writer.writeEndElement();
    }
}
