/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import java.util.Collection;

public class OAIPMHErrors implements OAIPMHContent {
    private final Collection<OAIPMHError> errors;

    public OAIPMHErrors(Collection<OAIPMHError> errors) {
        this.errors = errors;
    }

    public Collection<OAIPMHError> getErrors() {
        return errors;
    }
}
