/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class ListIdentifiers implements OAIPMHContent {

    private final List<Header> headers;
    private final Optional<ResumptionToken> resumptionToken;

    public ListIdentifiers(List<Header> headers, Optional<ResumptionToken> resumptionToken) {
        this.headers = headers;
        this.resumptionToken = resumptionToken;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public Optional<ResumptionToken> getResumptionToken() {
        return resumptionToken;
    }

    //    @Override
//    public void write(XmlDataProviderWriter writer) throws XmlWriteException {
//        try {
//            if (this.headers != null && !this.headers.isEmpty()) {
//                for (Header header : this.headers) {
//                    writer.writeStartElement("header");
//                    header.write(writer);
//                    writer.writeEndElement();
//                }
//            }
//
//            if (this.resumptionToken != null) {
//                writer.writeStartElement("resumptionToken");
//                this.resumptionToken.write(writer);
//                writer.writeEndElement();
//            }
//        } catch (XMLStreamException e) {
//            throw new XmlWriteException(e);
//        }
//    }

}
