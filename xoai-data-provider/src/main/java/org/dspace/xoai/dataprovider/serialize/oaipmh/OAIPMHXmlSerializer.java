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
import org.dspace.xoai.model.oaipmh.OAIPMH;
import org.dspace.xoai.xml.XSISchema;

public class OAIPMHXmlSerializer implements XmlSerializer<OAIPMH> {
    public static final String SCHEMA_LOCATION = "schemaLocation";
    public static final String OAI_NAMESPACE_URI = "http://www.openarchives.org/OAI/2.0/";
    public static final String OAI_SCHEMA_LOCATION = "http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd";
    public static final String OAI_PMH = "OAI-PMH";
    public static final String RESPONSE_DATE = "responseDate";

    private final DateTimeFormatter DateTimeFormatter;
    private final RequestXmlSerializer requestXmlSerializer;
    private final OAIPMHContentXmlSerializer oaipmhContentXmlSerializer;

    public OAIPMHXmlSerializer(DateTimeFormatter DateTimeFormatter, RequestXmlSerializer requestXmlSerializer, OAIPMHContentXmlSerializer oaipmhContentXmlSerializer) {
        this.DateTimeFormatter = DateTimeFormatter;
        this.requestXmlSerializer = requestXmlSerializer;
        this.oaipmhContentXmlSerializer = oaipmhContentXmlSerializer;
    }

    @Override
    public void serialize(XmlWriter writer, OAIPMH input) {
        writer.writeStartElement(OAI_PMH);
        writer.writeDefaultNamespace(OAI_NAMESPACE_URI);
        writer.writeNamespace(XSISchema.PREFIX, XSISchema.NAMESPACE_URI);
        writer.writeAttribute(XSISchema.PREFIX, XSISchema.NAMESPACE_URI, SCHEMA_LOCATION, String.format("%s %s", OAI_NAMESPACE_URI, OAI_SCHEMA_LOCATION));

        writer.writeElement(RESPONSE_DATE, DateTimeFormatter.format(input.getResponseDate()));
        requestXmlSerializer.serialize(writer, input.getRequest());


        oaipmhContentXmlSerializer.serialize(writer, input.getContent());

        writer.writeEndElement();
    }
}
