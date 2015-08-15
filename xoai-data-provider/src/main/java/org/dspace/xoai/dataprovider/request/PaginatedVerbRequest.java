/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.request;

public abstract class PaginatedVerbRequest implements VerbRequest {
    private final int offset;

    public PaginatedVerbRequest(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }
}
