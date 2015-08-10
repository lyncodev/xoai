/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.model;

import org.dspace.xoai.dataprovider.exceptions.InternalOAIException;
import org.dspace.xoai.dataprovider.filter.FilterResolver;
import org.dspace.xoai.dataprovider.model.conditions.Condition;

import javax.xml.transform.Transformer;
import java.util.ArrayList;
import java.util.List;

public class DataProviderContext {
    public static DataProviderContext context () {
        return new DataProviderContext();
    }

    private Transformer metadataTransformer;
    private List<MetadataFormat> metadataFormats = new ArrayList<MetadataFormat>();
    private List<Set> sets = new ArrayList<Set>();
    private Condition condition;

    public List<Set> getSets() {
        return sets;
    }

    public DataProviderContext withSet(Set set) {
        if (!set.hasCondition())
            throw new InternalOAIException("Context sets must have a condition");
        this.sets.add(set);
        return this;
    }

    public Transformer getTransformer() {
        return metadataTransformer;
    }

    public DataProviderContext withTransformer(Transformer metadataTransformer) {
        this.metadataTransformer = metadataTransformer;
        return this;
    }

    public List<MetadataFormat> getMetadataFormats() {
        return metadataFormats;
    }

    public DataProviderContext withMetadataFormat(MetadataFormat metadataFormat) {
        int remove = -1;
        for (int i = 0;i<metadataFormats.size();i++)
            if (metadataFormats.get(i).getPrefix().equals(metadataFormat.getPrefix()))
                remove = i;
        if (remove >= 0)
            this.metadataFormats.remove(remove);
        this.metadataFormats.add(metadataFormat);
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public DataProviderContext withCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public MetadataFormat formatForPrefix(String metadataPrefix) {
        for (MetadataFormat format : this.metadataFormats)
            if (format.getPrefix().equals(metadataPrefix))
                return format;

        return null;
    }

    public boolean hasTransformer() {
        return metadataTransformer != null;
    }

    public boolean hasCondition() {
        return this.condition != null;
    }

    public boolean isStaticSet(String setSpec) {
        for (Set set : this.sets)
            if (set.getSpec().equals(setSpec))
                return true;

        return false;
    }

    public Set getSet(String setSpec) {
        for (Set set : this.sets)
            if (set.getSpec().equals(setSpec))
                return set;

        return null;
    }

    public boolean hasSet(String set) {
        return isStaticSet(set);
    }

    public DataProviderContext withMetadataFormat(String prefix, Transformer transformer) {
        withMetadataFormat(new MetadataFormat().withNamespace(prefix).withPrefix(prefix).withSchemaLocation(prefix).withTransformer(transformer));
        return this;
    }

    public DataProviderContext withMetadataFormat(String prefix, Transformer transformer, Condition condition) {
        withMetadataFormat(
                new MetadataFormat()
                        .withNamespace(prefix)
                        .withPrefix(prefix)
                        .withSchemaLocation(prefix)
                        .withTransformer(transformer)
                        .withCondition(condition)
        );
        return this;
    }

    public DataProviderContext withoutMetadataFormats() {
        metadataFormats.clear();
        return this;
    }

    public List<MetadataFormat> formatFor(FilterResolver resolver, ItemIdentifier item) {
        List<MetadataFormat> result = new ArrayList<MetadataFormat>();
        for (MetadataFormat format : this.metadataFormats)
            if (!format.hasCondition() || format.getCondition().getFilter(resolver).isItemShown(item))
                result.add(format);
        return result;
    }
}