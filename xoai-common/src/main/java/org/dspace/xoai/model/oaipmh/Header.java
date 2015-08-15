/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Date;

public class Header {
    private final String identifier;
    private final Date datestamp;
    private final Collection<String> setSpec;
    private final Optional<Header.Status> status;

    public Header(String identifier, Date datestamp, Collection<String> setSpec, Optional<Header.Status> status) {
        this.identifier = identifier;
        this.datestamp = datestamp;
        this.setSpec = setSpec;
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }
    public Date getDatestamp() {
        return datestamp;
    }
    public Collection<String> getSetSpecs() {
        return this.setSpec;
    }
    public Optional<Header.Status> getStatus() {
        return status;
    }
    public boolean isDeleted () {
        return this.status != null;
    }

    public enum Status {
        DELETED("deleted");

        private final String representation;

        Status(String representation) {
            this.representation = representation;
        }

        public String value() {
            return representation;
        }

        public static Status fromRepresentation(String representation) {
            for (Status status : Status.values()) {
                if (status.representation.equals(representation)) {
                    return status;
                }
            }
            throw new IllegalArgumentException(representation);
        }
    }
}
