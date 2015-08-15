/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.request;

import com.google.common.base.Optional;

public class ListIdentifiersRequest extends PaginatedVerbRequest {
    private final RequestedMetadataPrefix requestedMetadataPrefix;
    private final Optional<RequestedDate> from;
    private final Optional<RequestedDate> until;
    private final Optional<RequestedSet> requestedSet;

    public ListIdentifiersRequest(int offset, RequestedMetadataPrefix requestedMetadataPrefix, Optional<RequestedDate> from, Optional<RequestedDate> until, Optional<RequestedSet> requestedSet) {
        super(offset);
        this.requestedMetadataPrefix = requestedMetadataPrefix;
        this.from = from;
        this.until = until;
        this.requestedSet = requestedSet;
    }

    public RequestedMetadataPrefix getRequestedMetadataPrefix() {
        return requestedMetadataPrefix;
    }

    public Optional<RequestedDate> getFrom() {
        return from;
    }

    public Optional<RequestedDate> getUntil() {
        return until;
    }

    public Optional<RequestedSet> getRequestedSet() {
        return requestedSet;
    }
}
