/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.request;

public class GetRecordRequest implements VerbRequest {
    private final RequestedIdentifier requestedIdentifier;
    private final RequestedMetadataPrefix requestedMetadataPrefix;

    public GetRecordRequest(RequestedIdentifier requestedIdentifier, RequestedMetadataPrefix requestedMetadataPrefix) {
        this.requestedIdentifier = requestedIdentifier;
        this.requestedMetadataPrefix = requestedMetadataPrefix;
    }

    public RequestedIdentifier getRequestedIdentifier() {
        return requestedIdentifier;
    }

    public RequestedMetadataPrefix getRequestedMetadataPrefix() {
        return requestedMetadataPrefix;
    }
}
