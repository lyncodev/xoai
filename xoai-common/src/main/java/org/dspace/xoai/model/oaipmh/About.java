/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

public class About {
    private final String value;

    public About(String xmlValue) {
        this.value = xmlValue;
    }

    public String getValue() {
        return value;
    }
}
