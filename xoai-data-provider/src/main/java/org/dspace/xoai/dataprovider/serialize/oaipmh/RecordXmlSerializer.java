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
import org.dspace.xoai.model.oaipmh.About;
import org.dspace.xoai.model.oaipmh.Record;

public class RecordXmlSerializer implements XmlSerializer<Record> {
    private static final String RECORD = "record";

    private final HeaderXmlSerializer headerSerializer;
    private final MetadataXmlSerializer metadataXmlSerializer;
    private final AboutXmlSerializer aboutXmlSerializer;

    public RecordXmlSerializer(HeaderXmlSerializer headerSerializer, MetadataXmlSerializer metadataXmlSerializer, AboutXmlSerializer aboutXmlSerializer) {
        this.headerSerializer = headerSerializer;
        this.metadataXmlSerializer = metadataXmlSerializer;
        this.aboutXmlSerializer = aboutXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, Record input) {
        xmlWriter.writeStartElement(RECORD);

        headerSerializer.serialize(xmlWriter, input.getHeader());

        if (input.getMetadata().isPresent()) {
            metadataXmlSerializer.serialize(xmlWriter, input.getMetadata().get());
        }

        for (About about : input.getAbouts()) {
            aboutXmlSerializer.serialize(xmlWriter, about);
        }

        xmlWriter.writeEndElement();
    }
}
