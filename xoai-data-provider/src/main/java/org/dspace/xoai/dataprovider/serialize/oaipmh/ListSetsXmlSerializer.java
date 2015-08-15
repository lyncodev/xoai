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
import org.dspace.xoai.model.oaipmh.ListSets;
import org.dspace.xoai.model.oaipmh.Set;

public class ListSetsXmlSerializer implements XmlSerializer<ListSets> {
    public static final String LIST_SETS = "ListSets";
    private final SetXmlSerializer setXmlSerializer;
    private final ResumptionTokenXmlSerializer resumptionTokenXmlSerializer;

    public ListSetsXmlSerializer(SetXmlSerializer setXmlSerializer, ResumptionTokenXmlSerializer resumptionTokenXmlSerializer) {
        this.setXmlSerializer = setXmlSerializer;
        this.resumptionTokenXmlSerializer = resumptionTokenXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, ListSets input) {
        xmlWriter.writeStartElement(LIST_SETS);
        for (Set set : input.getSets()) {
            setXmlSerializer.serialize(xmlWriter, set);
        }

        if (input.getResumptionToken().isPresent()) {
            resumptionTokenXmlSerializer.serialize(xmlWriter, input.getResumptionToken().get());
        }

        xmlWriter.writeEndElement();
    }
}
