/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.xoai;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.xoai.Field;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FieldXmlSerializerTest {
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private FieldXmlSerializer underTest = new FieldXmlSerializer();

    @Test
    public void serialize() throws Exception {
        String value = "value";
        String name = "name";

        underTest.serialize(xmlWriter, new Field(Optional.of(name), value));

        verify(xmlWriter).writeStartElement("field");
        verify(xmlWriter).writeAttribute("name", name);
        verify(xmlWriter).writeCharacters(value);
        verify(xmlWriter).writeEndElement();
    }
}