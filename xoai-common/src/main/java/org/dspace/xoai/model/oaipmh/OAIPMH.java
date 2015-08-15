/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Date;


public class OAIPMH {
    private final Date responseDate;
    private final Request request;
    private final OAIPMHContent content;

    public OAIPMH(Date responseDate, Request request, OAIPMHContent content) {
        this.responseDate = responseDate;
        this.request = request;
        this.content = content;
    }

    public Date getResponseDate() {
        return responseDate;
    }
    public Request getRequest() {
        return request;
    }

    public OAIPMHContent getContent() {
        return content;
    }
    //
//    @Override
//    public void write(XmlDataProviderWriter writer) throws XmlWriteException {
//        try {
//            writer.writeStartElement("OAI-PMH");
//            writer.writeDefaultNamespace(NAMESPACE_URI);
//            writer.writeNamespace(XSISchema.PREFIX, XSISchema.NAMESPACE_URI);
//            writer.writeAttribute(XSISchema.PREFIX, XSISchema.NAMESPACE_URI, "schemaLocation",
//                    NAMESPACE_URI + " " + SCHEMA_LOCATION);
//
//            writer.writeElement("responseDate", this.responseDate, Granularity.Second);
//            writer.writeElement("request", request);
//
//            if (!errors.isEmpty()) {
//                for (Error error : errors)
//                    writer.writeElement("error", error);
//            } else {
//                if (verb == null) throw new XmlWriteException("An error or a valid response must be set");
//                writer.writeElement(verb.getType().displayName(), verb);
//            }
//
//            writer.writeEndElement();
//        } catch (XMLStreamException e) {
//            throw new XmlWriteException(e);
//        }
//    }
}
