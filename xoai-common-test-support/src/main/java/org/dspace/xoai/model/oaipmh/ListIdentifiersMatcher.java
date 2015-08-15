/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;
import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(ListIdentifiers.class)
public final class ListIdentifiersMatcher extends CompositePropertyMatcher<ListIdentifiers> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a ListIdentifiers";
    private final PropertyMatcher<List<Header>> headersMatcher = new ReflectingPropertyMatcher<List<Header>>("headers", this);
    private final PropertyMatcher<Optional<ResumptionToken>> resumptionTokenMatcher = new ReflectingPropertyMatcher<Optional<ResumptionToken>>("resumptionToken", this);

    private ListIdentifiersMatcher(final String matchedObjectDescription, final ListIdentifiers template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasHeaders(template.getHeaders());
            hasResumptionToken(template.getResumptionToken());
        }
    }

    public static ListIdentifiersMatcher aListIdentifiersThat() {
        return new ListIdentifiersMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static ListIdentifiersMatcher aListIdentifiersLike(final ListIdentifiers template) {
        return new ListIdentifiersMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public ListIdentifiersMatcher hasHeaders(final List<Header> headers) {
        return hasHeaders(equalTo(headers));
    }

    public ListIdentifiersMatcher hasHeaders(final Matcher<? super List<Header>> headersMatcher) {
        this.headersMatcher.setMatcher(headersMatcher);
        return this;
    }

    public ListIdentifiersMatcher hasResumptionToken(final Optional<ResumptionToken> resumptionToken) {
        return hasResumptionToken(equalTo(resumptionToken));
    }

    public ListIdentifiersMatcher hasResumptionToken(final Matcher<? super Optional<ResumptionToken>> resumptionTokenMatcher) {
        this.resumptionTokenMatcher.setMatcher(resumptionTokenMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final ListIdentifiers item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
