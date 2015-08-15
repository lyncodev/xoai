/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.request;

public class RequestedIdentifier {
    private final String value;

    public RequestedIdentifier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
