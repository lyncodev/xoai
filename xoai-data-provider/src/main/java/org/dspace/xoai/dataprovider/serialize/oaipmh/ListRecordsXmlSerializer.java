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
import org.dspace.xoai.model.oaipmh.ListRecords;
import org.dspace.xoai.model.oaipmh.Record;

public class ListRecordsXmlSerializer implements XmlSerializer<ListRecords> {
    private static final String LIST_RECORDS = "ListRecords";

    private final RecordXmlSerializer recordXmlSerializer;
    private final ResumptionTokenXmlSerializer resumptionTokenXmlSerializer;


    public ListRecordsXmlSerializer(RecordXmlSerializer recordXmlSerializer, ResumptionTokenXmlSerializer resumptionTokenXmlSerializer) {
        this.recordXmlSerializer = recordXmlSerializer;
        this.resumptionTokenXmlSerializer = resumptionTokenXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, ListRecords input) {
        xmlWriter.writeStartElement(LIST_RECORDS);

        for (Record record : input.getRecords()) {
            recordXmlSerializer.serialize(xmlWriter, record);
        }

        if (input.getResumptionToken().isPresent()) {
            resumptionTokenXmlSerializer.serialize(xmlWriter, input.getResumptionToken().get());
        }

        xmlWriter.writeEndElement();
    }
}
