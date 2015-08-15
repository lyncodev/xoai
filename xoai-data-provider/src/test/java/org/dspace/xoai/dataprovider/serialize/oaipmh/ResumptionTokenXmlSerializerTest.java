/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.date.DateTimeFormatter;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ResumptionTokenXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResumptionTokenXmlSerializerTest {
    private final DateTimeFormatter dateTimeFormatter = mock(DateTimeFormatter.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private ResumptionTokenXmlSerializer underTest = new ResumptionTokenXmlSerializer(dateTimeFormatter);

    @Test
    public void serialize() throws Exception {
        Date date = mock(Date.class);
        long completeListSize = 1L;
        long cursor = 2L;

        String dateText = "dateText";
        String value = "value";
        when(dateTimeFormatter.apply(date)).thenReturn(dateText);

        underTest.serialize(xmlWriter, new ResumptionToken(Optional.of(value), Optional.of(date), Optional.of(completeListSize), Optional.of(cursor)));

        verify(xmlWriter).writeStartElement("resumptionToken");
        verify(xmlWriter).writeCharacters(value);
        verify(xmlWriter).writeEndElement();
        ArgumentCaptor<String> name = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argValue = ArgumentCaptor.forClass(String.class);
        verify(xmlWriter, times(3)).writeAttribute(name.capture(), argValue.capture());

        assertThat(name.getAllValues(), contains("expirationDate", "completeListSize", "cursor"));
        assertThat(argValue.getAllValues(), contains(dateText, "1", "2"));
    }

    @Test
    public void integration() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XmlWriter writer = new XmlWriter(output);
        writer.writeStartDocument();
        underTest.serialize(writer, new ResumptionToken(Optional.<String>absent(), Optional.<Date>absent(), Optional.<Long>absent(), Optional.<Long>absent()));
        writer.writeEndDocument();
        writer.flush();
        writer.close();

        assertThat(output.toString(), containsString("<resumptionToken/>"));
    }
}