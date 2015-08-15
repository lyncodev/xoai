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
import org.dspace.xoai.model.xoai.XOAIMetadata;
import org.dspace.xoai.xml.XSISchema;

public class XOAIMetadataXmlSerializer implements XmlSerializer<XOAIMetadata> {
    public static final String XOAI_NAMESPACE_URI = "http://www.lyncode.com/xoai";

    private static final String XOAI_SCHEMA_LOCATION = "http://www.lyncode.com/xsd/xoai.xsd";
    private static final String METADATA = "metadata";
    private static final String SCHEMA_LOCATION = "schemaLocation";

    private final ElementXmlSerializer elementXmlSerializer;

    public XOAIMetadataXmlSerializer(ElementXmlSerializer elementXmlSerializer) {
        this.elementXmlSerializer = elementXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter writer, XOAIMetadata input) {
        writer.setDefaultNamespace(XOAI_NAMESPACE_URI);
        writer.writeStartElement(METADATA);
        writer.writeNamespace(XSISchema.PREFIX, XSISchema.NAMESPACE_URI);
        writer.writeAttribute(XSISchema.PREFIX, XSISchema.NAMESPACE_URI, SCHEMA_LOCATION,
                String.format("%s %s", XOAI_NAMESPACE_URI, XOAI_SCHEMA_LOCATION));

        for (Element element : input.getElements()) {
            elementXmlSerializer.serialize(writer, element);
        }
        writer.writeEndElement();
    }
}
