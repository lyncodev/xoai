/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import org.dspace.xoai.model.oaipmh.ListMetadataFormats;
import org.dspace.xoai.model.oaipmh.MetadataFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListMetadataFormatsBuilder {
    private final List<MetadataFormat> metadataFormats = new ArrayList<MetadataFormat>();

    private ListMetadataFormatsBuilder() {
    }

    public static ListMetadataFormatsBuilder aListMetadataFormats() {
        return new ListMetadataFormatsBuilder();
    }

    public ListMetadataFormatsBuilder withMetadataFormats(Collection<MetadataFormat> metadataFormats) {
        this.metadataFormats.addAll(metadataFormats);
        return this;
    }

    public ListMetadataFormatsBuilder but() {
        return aListMetadataFormats().withMetadataFormats(metadataFormats);
    }

    public ListMetadataFormats build() {
        ListMetadataFormats listMetadataFormats = new ListMetadataFormats(metadataFormats);
        return listMetadataFormats;
    }
}
