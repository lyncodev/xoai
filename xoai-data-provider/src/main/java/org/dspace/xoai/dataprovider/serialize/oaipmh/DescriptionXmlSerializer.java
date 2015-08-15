/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.serialize.XmlStringXmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Description;

public class DescriptionXmlSerializer implements XmlSerializer<Description> {
    private final XmlStringXmlSerializer xmlStringSerializer;

    public DescriptionXmlSerializer(XmlStringXmlSerializer xmlStringSerializer) {
        this.xmlStringSerializer = xmlStringSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, Description input) {
        xmlStringSerializer.serialize(xmlWriter, input.getXml());
    }
}
