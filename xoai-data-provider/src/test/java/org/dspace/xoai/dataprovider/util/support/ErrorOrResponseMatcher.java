package org.dspace.xoai.dataprovider.util.support;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(ErrorOrResponse.class)
public final class ErrorOrResponseMatcher<P1, P2> extends CompositePropertyMatcher<ErrorOrResponse<P1, P2>> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "an ErrorOrResponse";
    private final PropertyMatcher<P2> responseMatcher = new ReflectingPropertyMatcher<P2>("response", this);
    private final PropertyMatcher<P1> errorMatcher = new ReflectingPropertyMatcher<P1>("error", this);
    private final PropertyMatcher<Boolean> okMatcher = new ReflectingPropertyMatcher<Boolean>("ok", this);

    private ErrorOrResponseMatcher(final String matchedObjectDescription, final ErrorOrResponse<P1, P2> template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasResponse(template.getResponse());
            hasError(template.getError());
            if (template.isOk()) {
                isOk();
            } else {
                isError();
            }
        }
    }

    public static <P1, P2> ErrorOrResponseMatcher<P1, P2> anErrorOrResponseThat() {
        return new ErrorOrResponseMatcher<P1, P2>(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static <P1, P2> ErrorOrResponseMatcher<P1, P2> anErrorOrResponseLike(final ErrorOrResponse<P1, P2> template) {
        return new ErrorOrResponseMatcher<P1, P2>(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public ErrorOrResponseMatcher<P1, P2> hasResponse(final P2 response) {
        return hasResponse(equalTo(response));
    }

    public ErrorOrResponseMatcher<P1, P2> hasResponse(final Matcher<? super P2> responseMatcher) {
        this.responseMatcher.setMatcher(responseMatcher);
        return this;
    }

    public ErrorOrResponseMatcher<P1, P2> hasError(final P1 error) {
        return hasError(equalTo(error));
    }

    public ErrorOrResponseMatcher<P1, P2> hasError(final Matcher<? super P1> errorMatcher) {
        this.errorMatcher.setMatcher(errorMatcher);
        return this;
    }

    public ErrorOrResponseMatcher<P1, P2> isOk() {
        return hasOk(equalTo(true));
    }
    public ErrorOrResponseMatcher<P1, P2> isError() {
        return hasOk(equalTo(false));
    }

    private ErrorOrResponseMatcher<P1, P2> hasOk(final Matcher<? super Boolean> okMatcher) {
        this.okMatcher.setMatcher(okMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final ErrorOrResponse<P1, P2> item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
