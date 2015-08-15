/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.serialize.oaipmh.OAIPMHErrorXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.OAIPMHError;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class OAIPMHErrorXmlSerializerTest {
    private XmlWriter xmlWriter = mock(XmlWriter.class);
    private OAIPMHErrorXmlSerializer underTest = new OAIPMHErrorXmlSerializer();

    @Test
    public void serializeWhenCodeExists() throws Exception {
        OAIPMHError.Code code = OAIPMHError.Code.BAD_ARGUMENT;
        Optional<OAIPMHError.Code> codeOptional = Optional.of(code);
        String message = "message";

        underTest.serialize(xmlWriter, new OAIPMHError(message, codeOptional));

        verify(xmlWriter).writeStartElement("error");
        verify(xmlWriter).writeAttribute("code", code.code());
        verify(xmlWriter).writeCharacters(message);
        verify(xmlWriter).writeEndElement();
    }

    @Test
    public void serializeWhenCodeNotExists() throws Exception {
        Optional<OAIPMHError.Code> codeOptional = Optional.absent();
        String message = "message";

        underTest.serialize(xmlWriter, new OAIPMHError(message, codeOptional));

        verify(xmlWriter).writeStartElement("error");
        verify(xmlWriter, never()).writeAttribute(anyString(), anyString());
        verify(xmlWriter).writeCharacters(message);
        verify(xmlWriter).writeEndElement();
    }
}