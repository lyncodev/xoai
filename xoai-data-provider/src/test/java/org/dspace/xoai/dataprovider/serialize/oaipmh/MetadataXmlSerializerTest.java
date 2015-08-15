/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.XmlStringXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.MetadataXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Metadata;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MetadataXmlSerializerTest {

    private final XmlStringXmlSerializer xmlStringSerializer = mock(XmlStringXmlSerializer.class);
    private MetadataXmlSerializer underTest = new MetadataXmlSerializer(xmlStringSerializer);

    @Test
    public void serialize() throws Exception {
        String xml = "xml";
        Metadata input = new Metadata(xml);
        XmlWriter xmlWriter = mock(XmlWriter.class);

        underTest.serialize(xmlWriter, input);

        verify(xmlWriter).writeStartElement("metadata");
        verify(xmlStringSerializer).serialize(xmlWriter, xml);
        verify(xmlWriter).writeEndElement();
    }
}