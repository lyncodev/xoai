/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.XmlStringXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.DescriptionXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Description;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DescriptionXmlSerializerTest {
    private XmlStringXmlSerializer stringSerializer = mock(XmlStringXmlSerializer.class);
    private DescriptionXmlSerializer underTest = new DescriptionXmlSerializer(stringSerializer);

    @Test
    public void serialize() throws Exception {
        String xml = "test";
        Description test = new Description(xml);

        XmlWriter xmlWriter = mock(XmlWriter.class);
        underTest.serialize(xmlWriter, test);

        verify(stringSerializer).serialize(xmlWriter, xml);
        verifyZeroInteractions(xmlWriter);
    }
}