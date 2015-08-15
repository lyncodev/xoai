/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.date.OAIDateFormatter;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Header;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class HeaderXmlSerializerTest {
    private final OAIDateFormatter dateFormatter = mock(OAIDateFormatter.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private HeaderXmlSerializer underTest = new HeaderXmlSerializer(dateFormatter);

    @Test
    public void serializeWhenStatusNotExists() throws Exception {

        String identifier = "id";
        Date datestamp = new Date();
        Set<String> sets = Collections.singleton("set");
        Optional<Header.Status> status = Optional.<Header.Status>absent();
        Header header = new Header(identifier, datestamp, sets, status);

        String date = "date";
        when(dateFormatter.apply(datestamp)).thenReturn(date);

        underTest.serialize(xmlWriter, header);

        verify(xmlWriter, never()).writeAttribute(anyString(), anyString());
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCaptor = ArgumentCaptor.forClass(String.class);
        verify(xmlWriter, times(3)).writeElement(nameCaptor.capture(), valueCaptor.capture());

        assertThat(nameCaptor.getAllValues(), contains("identifier", "datestamp", "setSpec"));
        assertThat(valueCaptor.getAllValues(), contains(identifier, date, "set"));
    }

    @Test
    public void serializeWhenStatusExists() throws Exception {

        String identifier = "id";
        Date datestamp = new Date();
        Set<String> sets = Collections.singleton("set");
        Optional<Header.Status> status = Optional.of(Header.Status.DELETED);
        Header header = new Header(identifier, datestamp, sets, status);

        String date = "date";
        when(dateFormatter.apply(datestamp)).thenReturn(date);

        underTest.serialize(xmlWriter, header);

        verify(xmlWriter).writeStartElement("header");
        verify(xmlWriter).writeAttribute("status", "deleted");
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCaptor = ArgumentCaptor.forClass(String.class);
        verify(xmlWriter, times(3)).writeElement(nameCaptor.capture(), valueCaptor.capture());
        verify(xmlWriter).writeEndElement();

        assertThat(nameCaptor.getAllValues(), contains("identifier", "datestamp", "setSpec"));
        assertThat(valueCaptor.getAllValues(), contains(identifier, date, "set"));
    }
}