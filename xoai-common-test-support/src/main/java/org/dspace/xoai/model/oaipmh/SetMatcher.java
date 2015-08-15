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

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Set.class)
public final class SetMatcher extends CompositePropertyMatcher<Set> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a Set";
    private final PropertyMatcher<String> specMatcher = new ReflectingPropertyMatcher<String>("spec", this);
    private final PropertyMatcher<String> nameMatcher = new ReflectingPropertyMatcher<String>("name", this);
    private final PropertyMatcher<List<Description>> descriptionsMatcher = new ReflectingPropertyMatcher<List<Description>>("descriptions", this);

    private SetMatcher(final String matchedObjectDescription, final Set template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasSpec(template.getSpec());
            hasName(template.getName());
            hasDescriptions(template.getDescriptions());
        }
    }

    public static SetMatcher aSetThat() {
        return new SetMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static SetMatcher aSetLike(final Set template) {
        return new SetMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public SetMatcher hasSpec(final String spec) {
        return hasSpec(equalTo(spec));
    }

    public SetMatcher hasSpec(final Matcher<? super String> specMatcher) {
        this.specMatcher.setMatcher(specMatcher);
        return this;
    }

    public SetMatcher hasName(final String name) {
        return hasName(equalTo(name));
    }

    public SetMatcher hasName(final Matcher<? super String> nameMatcher) {
        this.nameMatcher.setMatcher(nameMatcher);
        return this;
    }

    public SetMatcher hasDescriptions(final List<Description> descriptions) {
        return hasDescriptions(equalTo(descriptions));
    }

    public SetMatcher hasDescriptions(final Matcher<? super List<Description>> descriptionsMatcher) {
        this.descriptionsMatcher.setMatcher(descriptionsMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Set item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
