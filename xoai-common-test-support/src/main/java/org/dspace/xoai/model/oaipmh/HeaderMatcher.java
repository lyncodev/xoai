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
import org.dspace.xoai.model.oaipmh.Header.Status;
import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Header.class)
public final class HeaderMatcher extends CompositePropertyMatcher<Header> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a Header";
    private final PropertyMatcher<Status> statusMatcher = new ReflectingPropertyMatcher<Status>("status", this);
    private final PropertyMatcher<Collection<String>> setSpecsMatcher = new ReflectingPropertyMatcher<Collection<String>>("setSpecs", this);
    private final PropertyMatcher<Date> datestampMatcher = new ReflectingPropertyMatcher<Date>("datestamp", this);
    private final PropertyMatcher<Boolean> deletedMatcher = new ReflectingPropertyMatcher<Boolean>("deleted", this);
    private final PropertyMatcher<String> identifierMatcher = new ReflectingPropertyMatcher<String>("identifier", this);

    private HeaderMatcher(final String matchedObjectDescription, final Header template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasStatus(template.getStatus().orNull());
            hasSetSpecs(template.getSetSpecs());
            hasDatestamp(template.getDatestamp());
            hasDeleted(template.isDeleted());
            hasIdentifier(template.getIdentifier());
        }
    }

    public static HeaderMatcher aHeaderThat() {
        return new HeaderMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static HeaderMatcher aHeaderLike(final Header template) {
        return new HeaderMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public HeaderMatcher hasStatus(final Status status) {
        return hasStatus(equalTo(status));
    }

    public HeaderMatcher hasStatus(final Matcher<? super Status> statusMatcher) {
        this.statusMatcher.setMatcher(statusMatcher);
        return this;
    }

    public HeaderMatcher hasSetSpecs(final Collection<String> setSpecs) {
        return hasSetSpecs(equalTo(setSpecs));
    }

    public HeaderMatcher hasSetSpecs(final Matcher<? super Collection<String>> setSpecsMatcher) {
        this.setSpecsMatcher.setMatcher(setSpecsMatcher);
        return this;
    }

    public HeaderMatcher hasDatestamp(final Date datestamp) {
        return hasDatestamp(equalTo(datestamp));
    }

    public HeaderMatcher hasDatestamp(final Matcher<? super Date> datestampMatcher) {
        this.datestampMatcher.setMatcher(datestampMatcher);
        return this;
    }

    public HeaderMatcher hasDeleted(final boolean deleted) {
        return hasDeleted(equalTo(deleted));
    }

    public HeaderMatcher hasDeleted(final Matcher<? super Boolean> deletedMatcher) {
        this.deletedMatcher.setMatcher(deletedMatcher);
        return this;
    }

    public HeaderMatcher hasIdentifier(final String identifier) {
        return hasIdentifier(equalTo(identifier));
    }

    public HeaderMatcher hasIdentifier(final Matcher<? super String> identifierMatcher) {
        this.identifierMatcher.setMatcher(identifierMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Header item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
