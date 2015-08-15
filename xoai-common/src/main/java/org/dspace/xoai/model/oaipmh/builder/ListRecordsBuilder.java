/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import com.google.common.base.Optional;
import org.dspace.xoai.model.oaipmh.ListRecords;
import org.dspace.xoai.model.oaipmh.Record;
import org.dspace.xoai.model.oaipmh.ResumptionToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListRecordsBuilder {
    private final List<Record> records = new ArrayList<Record>();
    private Optional<ResumptionToken> resumptionToken = Optional.absent();

    private ListRecordsBuilder() {
    }

    public static ListRecordsBuilder aListRecords() {
        return new ListRecordsBuilder();
    }

    public ListRecordsBuilder withRecords(Collection<Record> records) {
        this.records.addAll(records);
        return this;
    }

    public ListRecordsBuilder withResumptionToken(ResumptionToken resumptionToken) {
        this.resumptionToken = Optional.fromNullable(resumptionToken);
        return this;
    }

    public ListRecords build() {
        return new ListRecords(records, resumptionToken);
    }
}
