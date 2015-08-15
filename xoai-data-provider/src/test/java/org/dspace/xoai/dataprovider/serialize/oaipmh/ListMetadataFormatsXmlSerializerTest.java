/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.oaipmh.ListMetadataFormatsXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.MetadataFormatXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.ListMetadataFormats;
import org.dspace.xoai.model.oaipmh.MetadataFormat;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListMetadataFormatsXmlSerializerTest {
    private final MetadataFormatXmlSerializer metadataFormatXmlSerializer = mock(MetadataFormatXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private ListMetadataFormatsXmlSerializer underTest = new ListMetadataFormatsXmlSerializer(metadataFormatXmlSerializer);

    @Test
    public void serialize() throws Exception {
        MetadataFormat metadataFormat = mock(MetadataFormat.class);

        underTest.serialize(xmlWriter, new ListMetadataFormats(Collections.singletonList(metadataFormat)));

        verify(xmlWriter).writeStartElement("ListMetadataFormats");
        verify(metadataFormatXmlSerializer).serialize(xmlWriter, metadataFormat);
        verify(xmlWriter).writeEndElement();
    }
}