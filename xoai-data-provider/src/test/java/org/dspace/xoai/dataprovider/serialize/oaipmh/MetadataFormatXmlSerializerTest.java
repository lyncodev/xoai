/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.oaipmh.MetadataFormatXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.MetadataFormat;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MetadataFormatXmlSerializerTest {
    private MetadataFormatXmlSerializer underTest = new MetadataFormatXmlSerializer();

    @Test
    public void serialize() throws Exception {
        String metadataNamespace = "metadataNamespace";
        String metadataPrefix = "metadataPrefix";
        String schema = "schema";

        XmlWriter xmlWriter = mock(XmlWriter.class);
        MetadataFormat metadataFormat = new MetadataFormat(metadataPrefix, schema, metadataNamespace);
        underTest.serialize(xmlWriter, metadataFormat);

        ArgumentCaptor<String> name = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> value = ArgumentCaptor.forClass(String.class);
        verify(xmlWriter, times(3)).writeElement(name.capture(), value.capture());
        verify(xmlWriter).writeStartElement("metadataFormat");
        verify(xmlWriter).writeEndElement();

        assertThat(name.getAllValues(), contains("metadataPrefix", "schema", "metadataNamespace"));
        assertThat(value.getAllValues(), contains(metadataPrefix, schema, metadataNamespace));
    }
}