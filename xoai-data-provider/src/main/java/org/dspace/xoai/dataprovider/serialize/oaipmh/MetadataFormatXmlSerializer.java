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
import org.dspace.xoai.model.oaipmh.MetadataFormat;

public class MetadataFormatXmlSerializer implements XmlSerializer<MetadataFormat> {
    private static final String METADATA_PREFIX = "metadataPrefix";
    private static final String SCHEMA = "schema";
    private static final String METADATA_NAMESPACE = "metadataNamespace";
    public static final String METADATA_FORMAT = "metadataFormat";

    @Override
    public void serialize(XmlWriter xmlWriter, MetadataFormat input) {
        xmlWriter.writeStartElement(METADATA_FORMAT);
        xmlWriter.writeElement(METADATA_PREFIX, input.getMetadataPrefix());
        xmlWriter.writeElement(SCHEMA, input.getSchema());
        xmlWriter.writeElement(METADATA_NAMESPACE, input.getMetadataNamespace());
        xmlWriter.writeEndElement();
    }
}
