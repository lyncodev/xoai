/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.repository;


import org.dspace.xoai.model.oaipmh.MetadataFormat;

import java.util.Collection;

public interface MetadataFormatRepository {
    Collection<MetadataFormat> getMetadataFormatsFor(String itemIdentifier);
    Collection<MetadataFormat> getMetadataFormats();
    boolean exists(String prefix);
}
