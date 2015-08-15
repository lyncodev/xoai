/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.xoai;

import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.xoai.Element;
import org.dspace.xoai.model.xoai.XOAIMetadata;
import org.dspace.xoai.xml.XSISchema;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class XOAIMetadataXmlSerializerTest {
    private final ElementXmlSerializer elementXmlSerializer = mock(ElementXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private XOAIMetadataXmlSerializer underTest = new XOAIMetadataXmlSerializer(elementXmlSerializer);

    @Test
    public void serialize() throws Exception {
        Element element = mock(Element.class);

        underTest.serialize(xmlWriter, new XOAIMetadata(Collections.singletonList(element)));

        verify(xmlWriter).setDefaultNamespace("http://www.lyncode.com/xoai");
        verify(xmlWriter).writeStartElement("metadata");
        verify(xmlWriter).writeNamespace(XSISchema.PREFIX, XSISchema.NAMESPACE_URI);
        verify(xmlWriter).writeAttribute(XSISchema.PREFIX, XSISchema.NAMESPACE_URI, "schemaLocation",
                "http://www.lyncode.com/xoai http://www.lyncode.com/xsd/xoai.xsd");
        verify(elementXmlSerializer).serialize(xmlWriter, element);
        verify(xmlWriter).writeEndElement();
    }
}