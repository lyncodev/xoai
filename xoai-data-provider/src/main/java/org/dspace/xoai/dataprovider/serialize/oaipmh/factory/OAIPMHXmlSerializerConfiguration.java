/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh.factory;

import org.dspace.xoai.dataprovider.date.DateTimeFormatter;
import org.dspace.xoai.dataprovider.date.OAIDateFormatter;

public class OAIPMHXmlSerializerConfiguration {
    private final DateTimeFormatter dateTimeFormatter;
    private final OAIDateFormatter OAIDateFormatter;

    public OAIPMHXmlSerializerConfiguration(DateTimeFormatter dateTimeFormatter, OAIDateFormatter OAIDateFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
        this.OAIDateFormatter = OAIDateFormatter;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public OAIDateFormatter getOAIDateFormatter() {
        return OAIDateFormatter;
    }

}
