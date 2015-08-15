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
import org.dspace.xoai.model.oaipmh.ListIdentifiers;
import org.dspace.xoai.model.oaipmh.ResumptionToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListIdentifiersBuilder {
    private List<Header> headers = new ArrayList<Header>();
    private Optional<ResumptionToken> resumptionToken = Optional.absent();

    private ListIdentifiersBuilder() {
    }

    public static ListIdentifiersBuilder aListIdentifiers() {
        return new ListIdentifiersBuilder();
    }

    public ListIdentifiersBuilder withHeaders(Collection<Header> headers) {
        this.headers.addAll(headers);
        return this;
    }

    public ListIdentifiersBuilder withResumptionToken(ResumptionToken resumptionToken) {
        this.resumptionToken = Optional.fromNullable(resumptionToken);
        return this;
    }

    public ListIdentifiers build() {
        return new ListIdentifiers(headers, resumptionToken);
    }
}
