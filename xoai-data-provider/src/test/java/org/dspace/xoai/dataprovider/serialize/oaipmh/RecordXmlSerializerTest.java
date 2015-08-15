/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.serialize.oaipmh.AboutXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.HeaderXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.MetadataXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.RecordXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.About;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.Metadata;
import org.dspace.xoai.model.oaipmh.Record;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class RecordXmlSerializerTest {
    private final HeaderXmlSerializer headerSerializer = mock(HeaderXmlSerializer.class);
    private final MetadataXmlSerializer metadataXmlSerializer = mock(MetadataXmlSerializer.class);
    private final AboutXmlSerializer aboutXmlSerializer = mock(AboutXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);

    private RecordXmlSerializer underTest = new RecordXmlSerializer(headerSerializer, metadataXmlSerializer, aboutXmlSerializer);

    @Test
    public void serializeWhenMetadataPresent() throws Exception {
        Header header = mock(Header.class);
        Metadata metadata = mock(Metadata.class);
        About about = mock(About.class);
        Optional<Metadata> metadataOptional = Optional.of(metadata);
        List<About> abouts = Collections.singletonList(about);

        Record record = new Record(header, metadataOptional, abouts);

        underTest.serialize(xmlWriter, record);

        verify(xmlWriter).writeStartElement("record");
        verify(headerSerializer).serialize(xmlWriter, header);
        verify(metadataXmlSerializer).serialize(xmlWriter, metadata);
        verify(aboutXmlSerializer).serialize(xmlWriter, about);
        verify(xmlWriter).writeEndElement();
    }

    @Test
    public void serializeWhenMetadataNotPresent() throws Exception {
        Header header = mock(Header.class);
        About about = mock(About.class);
        Optional<Metadata> metadataOptional = Optional.absent();
        List<About> abouts = Collections.singletonList(about);

        Record record = new Record(header, metadataOptional, abouts);

        underTest.serialize(xmlWriter, record);

        verify(xmlWriter).writeStartElement("record");
        verify(headerSerializer).serialize(xmlWriter, header);
        verify(metadataXmlSerializer, never()).serialize(any(XmlWriter.class), any(Metadata.class));
        verify(aboutXmlSerializer).serialize(xmlWriter, about);
        verify(xmlWriter).writeEndElement();
    }

    @Test
    public void serializeWhenNoAbout() throws Exception {
        Header header = mock(Header.class);
        About about = mock(About.class);
        Optional<Metadata> metadataOptional = Optional.absent();
        List<About> abouts = Collections.emptyList();

        Record record = new Record(header, metadataOptional, abouts);

        underTest.serialize(xmlWriter, record);

        verify(xmlWriter).writeStartElement("record");
        verify(headerSerializer).serialize(xmlWriter, header);
        verify(aboutXmlSerializer, never()).serialize(any(XmlWriter.class), any(About.class));
        verify(xmlWriter).writeEndElement();
    }
}