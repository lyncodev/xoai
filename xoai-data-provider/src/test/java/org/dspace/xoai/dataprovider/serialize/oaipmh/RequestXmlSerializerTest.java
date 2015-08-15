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
import org.dspace.xoai.dataprovider.serialize.oaipmh.RequestXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Request;
import org.dspace.xoai.model.oaipmh.VerbType;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RequestXmlSerializerTest {
    private final OAIDateFormatter dateFormatter = mock(OAIDateFormatter.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private RequestXmlSerializer underTest = new RequestXmlSerializer(dateFormatter);


    @Test
    public void serialize() throws Exception {
        String baseUrl = "baseUrlValue";
        Date from = mock(Date.class);
        Date until = mock(Date.class);
        String resumptionToken = "resumptionTokenValue";
        String set = "set";
        String metadataPrefix = "metadataPrefixValue";
        String identifier = "identifierValue";
        VerbType verb = VerbType.GetRecord;
        String fromValue = "fromValue";
        String untilValue = "untilValue";

        Request request = new Request(
                baseUrl,
                Optional.of(verb),
                Optional.of(identifier),
                Optional.of(metadataPrefix),
                Optional.of(from),
                Optional.of(until),
                Optional.of(set),
                Optional.of(resumptionToken)

        );

        when(dateFormatter.apply(from)).thenReturn(fromValue);
        when(dateFormatter.apply(until)).thenReturn(untilValue);

        underTest.serialize(xmlWriter, request);


        verify(xmlWriter).writeStartElement("request");
        verify(xmlWriter).writeCharacters(baseUrl);
        verify(xmlWriter).writeEndElement();


        ArgumentCaptor<String> name = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> value = ArgumentCaptor.forClass(String.class);
        verify(xmlWriter, times(7)).writeAttribute(name.capture(), value.capture());

        assertThat(name.getAllValues(), containsInAnyOrder("verb", "identifier", "metadataPrefix", "resumptionToken", "from", "until", "set"));
        assertThat(value.getAllValues(), containsInAnyOrder(verb.displayName(), identifier, metadataPrefix, resumptionToken, fromValue, untilValue, set));
    }
}