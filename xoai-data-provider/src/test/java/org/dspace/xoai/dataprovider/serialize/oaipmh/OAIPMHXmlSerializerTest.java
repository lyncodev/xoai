/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.date.DateTimeFormatter;
import org.dspace.xoai.dataprovider.serialize.oaipmh.OAIPMHContentXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.OAIPMHXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.RequestXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.OAIPMH;
import org.dspace.xoai.model.oaipmh.OAIPMHContent;
import org.dspace.xoai.model.oaipmh.Request;
import org.dspace.xoai.xml.XSISchema;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OAIPMHXmlSerializerTest {
    private final DateTimeFormatter dateFormatter = mock(DateTimeFormatter.class);
    private final RequestXmlSerializer requestXmlSerializer = mock(RequestXmlSerializer.class);
    private final OAIPMHContentXmlSerializer oaipmhContentXmlSerializer = mock(OAIPMHContentXmlSerializer.class);
    private final XmlWriter writer = mock(XmlWriter.class);
    private OAIPMHXmlSerializer underTest = new OAIPMHXmlSerializer(dateFormatter, requestXmlSerializer, oaipmhContentXmlSerializer);

    @Test
    public void serialize() throws Exception {
        String dateText = "date";
        Date date = mock(Date.class);
        Request request = mock(Request.class);
        OAIPMHContent content = mock(OAIPMHContent.class);

        when(dateFormatter.apply(date)).thenReturn(dateText);

        underTest.serialize(writer, new OAIPMH(date, request, content));

        verify(writer).writeStartElement("OAI-PMH");
        verify(writer).writeDefaultNamespace("http://www.openarchives.org/OAI/2.0/");
        verify(writer).writeNamespace(XSISchema.PREFIX, XSISchema.NAMESPACE_URI);
        verify(writer).writeAttribute(XSISchema.PREFIX, XSISchema.NAMESPACE_URI, "schemaLocation",
                "http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd");
        verify(writer).writeElement("responseDate", dateText);
        verify(requestXmlSerializer).serialize(writer, request);
        verify(oaipmhContentXmlSerializer).serialize(writer, content);
        verify(writer).writeEndElement();
    }
}