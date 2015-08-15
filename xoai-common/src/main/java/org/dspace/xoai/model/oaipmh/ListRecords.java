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

public class ListRecords implements OAIPMHContent {
    private final List<Record> records;
    private final Optional<ResumptionToken> resumptionToken;

    public ListRecords(List<Record> records, Optional<ResumptionToken> resumptionToken) {
        this.records = records;
        this.resumptionToken = resumptionToken;
    }

    public List<Record> getRecords() {
        return records;
    }
    public Optional<ResumptionToken> getResumptionToken() {
        return resumptionToken;
    }
}
