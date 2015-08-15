/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.oaipmh.GetRecordXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.RecordXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.GetRecord;
import org.dspace.xoai.model.oaipmh.Record;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetRecordXmlSerializerTest {
    private final RecordXmlSerializer recordXmlSerializer = mock(RecordXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private GetRecordXmlSerializer underTest = new GetRecordXmlSerializer(recordXmlSerializer);

    @Test
    public void serialize() throws Exception {

        Record record = mock(Record.class);

        underTest.serialize(xmlWriter, new GetRecord(record));

        verify(xmlWriter).writeStartElement("GetRecord");
        verify(recordXmlSerializer).serialize(xmlWriter, record);
        verify(xmlWriter).writeEndElement();
    }
}