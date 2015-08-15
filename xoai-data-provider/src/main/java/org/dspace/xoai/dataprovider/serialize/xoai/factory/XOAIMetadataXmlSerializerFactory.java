/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.xoai.factory;

import org.dspace.xoai.dataprovider.serialize.xoai.ElementXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.xoai.FieldXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.xoai.XOAIMetadataXmlSerializer;

public class XOAIMetadataXmlSerializerFactory {
    public XOAIMetadataXmlSerializer newInstance () {
        return new XOAIMetadataXmlSerializer(new ElementXmlSerializer(new FieldXmlSerializer()));
    }
}
