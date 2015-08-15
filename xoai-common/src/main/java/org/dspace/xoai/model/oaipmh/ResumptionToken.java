/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;

import java.util.Date;

public class ResumptionToken {
    private final Optional<String> value;
    private final Optional<Date> expirationDate;
    private final Optional<Long> completeListSize;
    private final Optional<Long> cursor;

    public ResumptionToken(Optional<String> value, Optional<Date> expirationDate, Optional<Long> completeListSize, Optional<Long> cursor) {
        this.value = value;
        this.expirationDate = expirationDate;
        this.completeListSize = completeListSize;
        this.cursor = cursor;
    }

    public Optional<String> getValue() {
        return value;
    }

    public Optional<Date> getExpirationDate() {
        return expirationDate;
    }

    public Optional<Long> getCompleteListSize() {
        return completeListSize;
    }

    public Optional<Long> getCursor() {
        return cursor;
    }
}
