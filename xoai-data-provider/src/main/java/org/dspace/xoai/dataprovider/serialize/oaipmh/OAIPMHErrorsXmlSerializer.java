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
import org.dspace.xoai.model.oaipmh.OAIPMHError;
import org.dspace.xoai.model.oaipmh.OAIPMHErrors;

public class OAIPMHErrorsXmlSerializer implements XmlSerializer<OAIPMHErrors> {
    private final OAIPMHErrorXmlSerializer oaipmhErrorXmlSerializer;

    public OAIPMHErrorsXmlSerializer(OAIPMHErrorXmlSerializer OAIPMHErrorXmlSerializer) {
        this.oaipmhErrorXmlSerializer = OAIPMHErrorXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, OAIPMHErrors input) {
        for (OAIPMHError error : input.getErrors()) {
            oaipmhErrorXmlSerializer.serialize(xmlWriter, error);
        }
    }
}
