/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import org.dspace.xoai.dataprovider.repository.DateTimeFormatter;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.Request;

public class RequestXmlSerializer implements XmlSerializer<Request> {
    private static final String REQUEST = "request";
    public static final String VERB = "verb";
    public static final String IDENTIFIER = "identifier";
    public static final String METADATA_PREFIX = "metadataPrefix";
    public static final String FROM = "from";
    public static final String UNTIL = "until";
    public static final String SET = "set";
    public static final String RESUMPTION_TOKEN = "resumptionToken";

    private final DateTimeFormatter DateTimeFormatter;

    public RequestXmlSerializer(OAIDateFormatter dateFormatter) {
        this.DateTimeFormatter = dateFormatter;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, Request input) {
        xmlWriter.writeStartElement(REQUEST);

        if (input.getVerb().isPresent()) {
            xmlWriter.writeAttribute(VERB, input.getVerb().get().displayName());
        }

        if (input.getIdentifier().isPresent()) {
            xmlWriter.writeAttribute(IDENTIFIER, input.getIdentifier().get());
        }

        if (input.getMetadataPrefix().isPresent()) {
            xmlWriter.writeAttribute(METADATA_PREFIX, input.getMetadataPrefix().get());
        }

        if (input.getFrom().isPresent()) {
            xmlWriter.writeAttribute(FROM, DateTimeFormatter.apply(input.getFrom().get()));
        }

        if (input.getUntil().isPresent()) {
            xmlWriter.writeAttribute(UNTIL, DateTimeFormatter.apply(input.getUntil().get()));
        }

        if (input.getSet().isPresent()) {
            xmlWriter.writeAttribute(SET, input.getSet().get());
        }

        if (input.getResumptionToken().isPresent()) {
            xmlWriter.writeAttribute(RESUMPTION_TOKEN, input.getResumptionToken().get());
        }

        xmlWriter.writeCharacters(input.getBaseUrl());

        xmlWriter.writeEndElement();
    }
}
