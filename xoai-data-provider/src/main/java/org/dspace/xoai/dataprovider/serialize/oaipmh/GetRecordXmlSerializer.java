/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.GetRecord;

public class GetRecordXmlSerializer implements XmlSerializer<GetRecord> {
    public static final String GET_RECORD = "GetRecord";
    private final RecordXmlSerializer recordXmlSerializer;

    public GetRecordXmlSerializer(RecordXmlSerializer recordXmlSerializer) {
        this.recordXmlSerializer = recordXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, GetRecord input) {
        xmlWriter.writeStartElement(GET_RECORD);
        recordXmlSerializer.serialize(xmlWriter, input.getRecord());
        xmlWriter.writeEndElement();
    }
}
