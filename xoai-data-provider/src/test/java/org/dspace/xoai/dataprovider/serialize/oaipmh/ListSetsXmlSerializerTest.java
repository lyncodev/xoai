/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ListSetsXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.ResumptionTokenXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.SetXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.ListSets;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.dspace.xoai.model.oaipmh.Set;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListSetsXmlSerializerTest {
    private final SetXmlSerializer setXmlSerializer = mock(SetXmlSerializer.class);
    private final ResumptionTokenXmlSerializer resumptionTokenXmlSerializer = mock(ResumptionTokenXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private ListSetsXmlSerializer underTest = new ListSetsXmlSerializer(setXmlSerializer, resumptionTokenXmlSerializer);

    @Test
    public void serialize() throws Exception {
        Set set = mock(Set.class);
        ResumptionToken resumptionToken = mock(ResumptionToken.class);

        underTest.serialize(xmlWriter, new ListSets(Collections.singletonList(set), Optional.of(resumptionToken)));

        verify(xmlWriter).writeStartElement("ListSets");
        verify(setXmlSerializer).serialize(xmlWriter, set);
        verify(resumptionTokenXmlSerializer).serialize(xmlWriter, resumptionToken);
        verify(xmlWriter).writeEndElement();
    }
}