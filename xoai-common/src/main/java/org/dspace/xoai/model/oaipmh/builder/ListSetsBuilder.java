/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import com.google.common.base.Optional;
import org.dspace.xoai.model.oaipmh.ListSets;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.dspace.xoai.model.oaipmh.Set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListSetsBuilder {
    private final List<Set> sets = new ArrayList<Set>();
    private ResumptionToken resumptionToken;

    private ListSetsBuilder() {
    }

    public static ListSetsBuilder aListSets() {
        return new ListSetsBuilder();
    }

    public ListSetsBuilder withSets(Collection<Set> sets) {
        this.sets.addAll(sets);
        return this;
    }

    public ListSetsBuilder withResumptionToken(ResumptionToken resumptionToken) {
        this.resumptionToken = resumptionToken;
        return this;
    }

    public ListSetsBuilder but() {
        return aListSets().withSets(sets).withResumptionToken(resumptionToken);
    }

    public ListSets build() {
        return new ListSets(sets, Optional.fromNullable(resumptionToken));
    }
}
