/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

public class Metadata {
    private final String xml;

    public Metadata(String value) {
        this.xml = value;
    }

//    @Override
//    public void write(XmlDataProviderWriter writer) throws XmlWriteException {
//        if (this.value != null)
//            this.value.write(writer);
//        else {
//            EchoElement elem = new EchoElement(string);
//            elem.write(writer);
//        }
//    }


    public String getXml() {
        return xml;
    }
}
