/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.request;

import com.google.common.base.Optional;

public class ListMetadataFormatsRequest implements VerbRequest {
    private final Optional<RequestedIdentifier> identifier;

    public ListMetadataFormatsRequest(Optional<RequestedIdentifier> identifier) {
        this.identifier = identifier;
    }

    public Optional<RequestedIdentifier> getIdentifier() {
        return identifier;
    }
}
