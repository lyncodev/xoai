package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;
import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(ListSets.class)
public final class ListSetsMatcher extends CompositePropertyMatcher<ListSets> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a ListSets";
    private final PropertyMatcher<Optional<ResumptionToken>> resumptionTokenMatcher = new ReflectingPropertyMatcher<Optional<ResumptionToken>>("resumptionToken", this);
    private final PropertyMatcher<Iterable<? extends Set>> setsMatcher = new ReflectingPropertyMatcher<Iterable<? extends Set>>("sets", this);

    private ListSetsMatcher(final String matchedObjectDescription) {
        super(matchedObjectDescription);
    }

    public static ListSetsMatcher aListSetsThat() {
        return new ListSetsMatcher(MATCHED_OBJECT_DESCRIPTION);
    }

    public ListSetsMatcher hasResumptionToken(final Optional<ResumptionToken> resumptionToken) {
        return hasResumptionToken(equalTo(resumptionToken));
    }

    public ListSetsMatcher hasResumptionToken(final Matcher<? super Optional<ResumptionToken>> resumptionTokenMatcher) {
        this.resumptionTokenMatcher.setMatcher(resumptionTokenMatcher);
        return this;
    }

    public ListSetsMatcher hasSets(final Matcher<Iterable<? extends Set>> setsMatcher) {
        this.setsMatcher.setMatcher(setsMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final ListSets item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
