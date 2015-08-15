/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Record.class)
public final class RecordMatcher extends CompositePropertyMatcher<Record> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a Record";
    private final PropertyMatcher<Collection<About>> aboutsMatcher = new ReflectingPropertyMatcher<Collection<About>>("abouts", this);
    private final PropertyMatcher<Header> headerMatcher = new ReflectingPropertyMatcher<Header>("header", this);
    private final PropertyMatcher<Metadata> metadataMatcher = new ReflectingPropertyMatcher<Metadata>("metadata", this);

    private RecordMatcher(final String matchedObjectDescription, final Record template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasAbouts(template.getAbouts());
            hasHeader(template.getHeader());
            hasMetadata(template.getMetadata().orNull());
        }
    }

    public static RecordMatcher aRecordThat() {
        return new RecordMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static RecordMatcher aRecordLike(final Record template) {
        return new RecordMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public RecordMatcher hasAbouts(final Collection<About> abouts) {
        return hasAbouts(equalTo(abouts));
    }

    public RecordMatcher hasAbouts(final Matcher<? super Collection<About>> aboutsMatcher) {
        this.aboutsMatcher.setMatcher(aboutsMatcher);
        return this;
    }

    public RecordMatcher hasHeader(final Header header) {
        return hasHeader(equalTo(header));
    }

    public RecordMatcher hasHeader(final Matcher<? super Header> headerMatcher) {
        this.headerMatcher.setMatcher(headerMatcher);
        return this;
    }

    public RecordMatcher hasMetadata(final Metadata metadata) {
        return hasMetadata(equalTo(metadata));
    }

    public RecordMatcher hasMetadata(final Matcher<? super Metadata> metadataMatcher) {
        this.metadataMatcher.setMatcher(metadataMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Record item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
