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
import org.dspace.xoai.model.oaipmh.ListMetadataFormats;
import org.dspace.xoai.model.oaipmh.MetadataFormat;

public class ListMetadataFormatsXmlSerializer implements XmlSerializer<ListMetadataFormats> {
    public static final String LIST_METADATA_FORMATS = "ListMetadataFormats";
    private final MetadataFormatXmlSerializer metadataFormatXmlSerializer;

    public ListMetadataFormatsXmlSerializer(MetadataFormatXmlSerializer metadataFormatXmlSerializer) {
        this.metadataFormatXmlSerializer = metadataFormatXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, ListMetadataFormats input) {
        xmlWriter.writeStartElement(LIST_METADATA_FORMATS);
        for (MetadataFormat metadataFormat : input.getMetadataFormats()) {
            metadataFormatXmlSerializer.serialize(xmlWriter, metadataFormat);
        }
        xmlWriter.writeEndElement();
    }
}
