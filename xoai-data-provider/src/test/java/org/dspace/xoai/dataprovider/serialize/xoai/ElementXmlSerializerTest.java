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
import org.dspace.xoai.model.xoai.Field;
import org.junit.Test;

import java.util.Collections;

import static org.dspace.xoai.dataprovider.serialize.xoai.XOAIMetadataXmlSerializer.XOAI_NAMESPACE_URI;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ElementXmlSerializerTest {
    private final FieldXmlSerializer fieldXmlSerializer = mock(FieldXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private ElementXmlSerializer underTest = new ElementXmlSerializer(fieldXmlSerializer);

    @Test
    public void serialize() throws Exception {
        String name = "name";
        Field field = mock(Field.class);

        underTest.serialize(xmlWriter, new Element(name, Collections.singletonList(field), Collections.<Element>emptyList()));

        verify(xmlWriter).writeStartElement(XOAI_NAMESPACE_URI, "element");
        verify(xmlWriter).writeAttribute("name", name);
        verify(fieldXmlSerializer).serialize(xmlWriter, field);
        verify(xmlWriter).writeEndElement();
    }
}