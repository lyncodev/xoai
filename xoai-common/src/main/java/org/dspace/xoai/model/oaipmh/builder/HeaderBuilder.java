/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import com.google.common.base.Optional;
import org.dspace.xoai.model.oaipmh.Header;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HeaderBuilder {
    private String identifier;
    private Date datestamp;
    private Collection<String> setSpec = new ArrayList<String>();
    private Header.Status status;

    private HeaderBuilder() {
    }

    public static HeaderBuilder aHeader() {
        return new HeaderBuilder();
    }

    public HeaderBuilder withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public HeaderBuilder withDatestamp(Date datestamp) {
        this.datestamp = datestamp;
        return this;
    }

    public HeaderBuilder withSetSpec(Collection<String> setSpec) {
        this.setSpec.addAll(setSpec);
        return this;
    }

    public HeaderBuilder withSetSpec(String setSpec) {
        this.setSpec.add(setSpec);
        return this;
    }

    public HeaderBuilder withStatus(Header.Status status) {
        this.status = status;
        return this;
    }

    public HeaderBuilder but() {
        return aHeader().withIdentifier(identifier).withDatestamp(datestamp).withSetSpec(setSpec).withStatus(status);
    }

    public Header build() {
        Header header = new Header(identifier, datestamp, setSpec, Optional.fromNullable(status));
        return header;
    }
}
