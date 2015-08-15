/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.serialize.oaipmh.HeaderXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ListIdentifiersXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ResumptionTokenXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.ListIdentifiers;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListIdentifiersXmlSerializerTest {
    private final HeaderXmlSerializer headerXmlSerializer = mock(HeaderXmlSerializer.class);
    private final ResumptionTokenXmlSerializer resumptionTokenXmlSerializer = mock(ResumptionTokenXmlSerializer.class);
    private final ListIdentifiersXmlSerializer underTest = new ListIdentifiersXmlSerializer(headerXmlSerializer, resumptionTokenXmlSerializer);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);

    @Test
    public void serialize() throws Exception {
        Header header = mock(Header.class);
        ResumptionToken resumptionToken = mock(ResumptionToken.class);

        underTest.serialize(xmlWriter, new ListIdentifiers(Collections.singletonList(header), Optional.of(resumptionToken)));

        verify(xmlWriter).writeStartElement("ListIdentifiers");
        verify(headerXmlSerializer).serialize(xmlWriter, header);
        verify(resumptionTokenXmlSerializer).serialize(xmlWriter, resumptionToken);
        verify(xmlWriter).writeEndElement();
    }
}