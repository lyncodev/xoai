/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh.factory;

import com.google.common.collect.ImmutableMap;
import org.dspace.xoai.dataprovider.date.DateTimeFormatter;
import org.dspace.xoai.dataprovider.date.OAIDateFormatter;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.serialize.XmlStringXmlSerializer;
import org.dspace.xoai.dataprovider.serialize.oaipmh.*;
import org.dspace.xoai.model.oaipmh.*;

public class OAIPMHXmlSerializerFactory {
    public OAIPMHXmlSerializer newInstance (OAIPMHXmlSerializerConfiguration configuration) {
        DateTimeFormatter dateTimeFormatter = configuration.getDateTimeFormatter();
        OAIDateFormatter oaiDateFormatter = configuration.getOAIDateFormatter();
        XmlStringXmlSerializer xmlStringSerializer = new XmlStringXmlSerializer();
        HeaderXmlSerializer headerSerializer = new HeaderXmlSerializer(oaiDateFormatter);
        RecordXmlSerializer recordXmlSerializer = new RecordXmlSerializer(headerSerializer, new MetadataXmlSerializer(xmlStringSerializer), new AboutXmlSerializer(xmlStringSerializer));
        ResumptionTokenXmlSerializer resumptionTokenXmlSerializer = new ResumptionTokenXmlSerializer(dateTimeFormatter);
        DescriptionXmlSerializer descriptionXmlSerializer = new DescriptionXmlSerializer(xmlStringSerializer);
        return new OAIPMHXmlSerializer(dateTimeFormatter, new RequestXmlSerializer(oaiDateFormatter), new OAIPMHContentXmlSerializer(ImmutableMap.<Class, XmlSerializer>builder()
                .put(OAIPMHErrors.class, new OAIPMHErrorsXmlSerializer(new OAIPMHErrorXmlSerializer()))
                .put(GetRecord.class, new GetRecordXmlSerializer(recordXmlSerializer))
                .put(ListMetadataFormats.class, new ListMetadataFormatsXmlSerializer(new MetadataFormatXmlSerializer()))
                .put(ListRecords.class, new ListRecordsXmlSerializer(recordXmlSerializer, resumptionTokenXmlSerializer))
                .put(ListSets.class, new ListSetsXmlSerializer(new SetXmlSerializer(descriptionXmlSerializer), resumptionTokenXmlSerializer))
                .put(ListIdentifiers.class, new ListIdentifiersXmlSerializer(headerSerializer, resumptionTokenXmlSerializer))
                .put(Identify.class, new IdentifyXmlSerializer(oaiDateFormatter, descriptionXmlSerializer))
                .build()));
    }
}
