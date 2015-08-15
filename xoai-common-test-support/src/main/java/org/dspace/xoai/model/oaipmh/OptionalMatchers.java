/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;
import org.hamcrest.*;
import org.hamcrest.Description;

public class OptionalMatchers {
    public static <T> Matcher<Optional<? super T>> value (final Matcher<? super T> matcher) {
        return new TypeSafeMatcher<Optional<? super T>>() {
            @Override
            protected boolean matchesSafely(Optional<? super T> optional) {
                if (optional.isPresent()) {
                    return matcher.matches(optional.get());
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendDescriptionOf(matcher);
            }
        };
    }

    public static Matcher<Optional> isAbsent () {
        return new TypeSafeMatcher<Optional>() {
            @Override
            protected boolean matchesSafely(Optional optional) {
                return !optional.isPresent();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("No value");
            }
        };
    }
}
