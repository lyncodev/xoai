/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import java.util.List;

public class ListMetadataFormats implements OAIPMHContent {
    private final List<MetadataFormat> metadataFormats;

    public ListMetadataFormats(List<MetadataFormat> metadataFormats) {
        this.metadataFormats = metadataFormats;
    }

//    @Override
//    public void write(XmlDataProviderWriter writer) throws XmlWriteException {
//        if (!this.metadataFormats.isEmpty())
//            for (MetadataFormat format : this.metadataFormats)
//                writer.writeElement("metadataFormat", format);
//    }

    public List<MetadataFormat> getMetadataFormats() {
        return metadataFormats;
    }
}
