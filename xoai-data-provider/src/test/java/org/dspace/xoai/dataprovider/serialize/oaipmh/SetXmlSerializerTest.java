/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.oaipmh.DescriptionXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.SetXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Description;
import org.dspace.xoai.model.oaipmh.Set;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SetXmlSerializerTest {
    private final DescriptionXmlSerializer descriptionXmlSerializer = mock(DescriptionXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private SetXmlSerializer underTest = new SetXmlSerializer(descriptionXmlSerializer);

    @Test
    public void serialize() throws Exception {

        Description description = mock(Description.class);
        String setName = "name";
        String spec = "spec";

        underTest.serialize(xmlWriter, new Set(spec, setName, Collections.singletonList(description)));

        ArgumentCaptor<String> start = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> name = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> value = ArgumentCaptor.forClass(String.class);
        verify(xmlWriter, times(2)).writeStartElement(start.capture());
        verify(xmlWriter, times(2)).writeElement(name.capture(), value.capture());
        verify(xmlWriter, times(2)).writeEndElement();

        assertThat(start.getAllValues(), contains("set", "setDescription"));
        assertThat(name.getAllValues(), contains("setSpec", "setName"));
        assertThat(value.getAllValues(), contains(spec, setName));
    }
}