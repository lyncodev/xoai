/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh.builder;

import com.google.common.base.Optional;
import org.dspace.xoai.model.oaipmh.About;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.Metadata;
import org.dspace.xoai.model.oaipmh.Record;

import java.util.ArrayList;
import java.util.Collection;

public class RecordBuilder {
    private Header header;
    private Metadata metadata;
    private final Collection<About> abouts = new ArrayList<About>();

    private RecordBuilder() {
    }

    public static RecordBuilder aRecord() {
        return new RecordBuilder();
    }

    public RecordBuilder withHeader(Header header) {
        this.header = header;
        return this;
    }

    public RecordBuilder withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public RecordBuilder withAbouts(Collection<About> abouts) {
        this.abouts.addAll(abouts);
        return this;
    }

    public RecordBuilder withAbout(About abouts) {
        this.abouts.add(abouts);
        return this;
    }

    public RecordBuilder but() {
        return aRecord().withHeader(header).withMetadata(metadata).withAbouts(abouts);
    }

    public Record build() {
        Record record = new Record(header, Optional.fromNullable(metadata), abouts);
        return record;
    }
}
