/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.oaipmh.OAIPMHErrorXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.OAIPMHErrorsXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.OAIPMHError;
import org.dspace.xoai.model.oaipmh.OAIPMHErrors;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OAIPMHErrorsXmlSerializerTest {
    private final OAIPMHErrorXmlSerializer oaipmhErrorXmlSerializer = mock(OAIPMHErrorXmlSerializer.class);
    private final XmlWriter xmlWriter = mock(XmlWriter.class);
    private OAIPMHErrorsXmlSerializer underTest = new OAIPMHErrorsXmlSerializer(oaipmhErrorXmlSerializer);

    @Test
    public void serialize() throws Exception {
        OAIPMHError oaipmhError = mock(OAIPMHError.class);

        underTest.serialize(xmlWriter, new OAIPMHErrors(Collections.singleton(oaipmhError)));

        verify(oaipmhErrorXmlSerializer).serialize(xmlWriter, oaipmhError);
    }
}