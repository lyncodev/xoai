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
import org.dspace.xoai.model.oaipmh.Description;
import org.dspace.xoai.model.oaipmh.Set;

public class SetXmlSerializer implements XmlSerializer<Set> {
    private static final String SET = "set";
    private static final String SET_SPEC = "setSpec";
    private static final String SET_NAME = "setName";
    private static final String SET_DESCRIPTION = "setDescription";

    private final DescriptionXmlSerializer descriptionXmlSerializer;

    public SetXmlSerializer(DescriptionXmlSerializer descriptionXmlSerializer) {
        this.descriptionXmlSerializer = descriptionXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, Set input) {
        xmlWriter.writeStartElement(SET);
        xmlWriter.writeElement(SET_SPEC, input.getSpec());
        xmlWriter.writeElement(SET_NAME, input.getName());

        for (Description description : input.getDescriptions()) {
            xmlWriter.writeStartElement(SET_DESCRIPTION);
            descriptionXmlSerializer.serialize(xmlWriter, description);
            xmlWriter.writeEndElement();
        }

        xmlWriter.writeEndElement();
    }
}
