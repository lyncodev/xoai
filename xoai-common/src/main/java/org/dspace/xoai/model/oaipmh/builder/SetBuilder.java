/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import org.dspace.xoai.model.oaipmh.Description;
import org.dspace.xoai.model.oaipmh.Set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SetBuilder {
    private String spec;
    private String name;
    private final List<Description> descriptions = new ArrayList<Description>();

    private SetBuilder() {
    }

    public static SetBuilder aSet() {
        return new SetBuilder();
    }

    public SetBuilder withSpec(String spec) {
        this.spec = spec;
        return this;
    }

    public SetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SetBuilder withDescriptions(Collection<Description> descriptions) {
        this.descriptions.addAll(descriptions);
        return this;
    }

    public Set build() {
        return new Set(spec, name, descriptions);
    }
}
