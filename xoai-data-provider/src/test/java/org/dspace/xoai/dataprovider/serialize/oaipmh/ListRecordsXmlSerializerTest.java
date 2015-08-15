/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ListRecordsXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.RecordXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ResumptionTokenXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.ListRecords;
import org.dspace.xoai.model.oaipmh.Record;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListRecordsXmlSerializerTest {
    private final RecordXmlSerializer recordXmlSerializer = mock(RecordXmlSerializer.class);
    private final ResumptionTokenXmlSerializer resumptionTokenXmlSerializer = mock(ResumptionTokenXmlSerializer.class);
    private final ListRecordsXmlSerializer underTest = new ListRecordsXmlSerializer(recordXmlSerializer, resumptionTokenXmlSerializer);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);

    @Test
    public void serialize() throws Exception {
        Record record = mock(Record.class);
        ResumptionToken resumptionToken = mock(ResumptionToken.class);

        underTest.serialize(xmlWriter, new ListRecords(Collections.singletonList(record), Optional.of(resumptionToken)));

        verify(xmlWriter).writeStartElement("ListRecords");
        verify(recordXmlSerializer).serialize(xmlWriter, record);
        verify(resumptionTokenXmlSerializer).serialize(xmlWriter, resumptionToken);
        verify(xmlWriter).writeEndElement();
    }
}