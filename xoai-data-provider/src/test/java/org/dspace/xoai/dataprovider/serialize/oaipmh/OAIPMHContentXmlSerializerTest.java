/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.collect.ImmutableMap;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.OAIPMHContentXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.GetRecord;
import org.dspace.xoai.model.oaipmh.OAIPMHErrors;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OAIPMHContentXmlSerializerTest {
    private final Objenesis objenesis = new ObjenesisStd();
    private final XmlSerializer xmlSerializer = mock(XmlSerializer.class);
    private final OAIPMHContentXmlSerializer underTest = new OAIPMHContentXmlSerializer(
            ImmutableMap.<Class, XmlSerializer>builder()
                    .put(OAIPMHErrors.class, xmlSerializer)
                    .build()
    );
    private final XmlWriter xmlWriter = mock(XmlWriter.class);

    @Test
    public void serialize() throws Exception {
        OAIPMHErrors instance = instanceOf(OAIPMHErrors.class);

        underTest.serialize(xmlWriter, instance);

        verify(xmlSerializer).serialize(xmlWriter, instance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void serializeWhenNotFund() throws Exception {
        GetRecord instance = instanceOf(GetRecord.class);

        underTest.serialize(xmlWriter, instance);
    }

    private <T> T instanceOf(Class<T> type) {
        return (T) objenesis.newInstance(type);
    }
}