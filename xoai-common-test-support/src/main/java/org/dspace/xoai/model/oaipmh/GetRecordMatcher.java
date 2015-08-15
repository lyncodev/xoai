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

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(GetRecord.class)
public final class GetRecordMatcher extends CompositePropertyMatcher<GetRecord> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a GetRecord";
    private final PropertyMatcher<Record> recordMatcher = new ReflectingPropertyMatcher<Record>("record", this);

    private GetRecordMatcher(final String matchedObjectDescription, final GetRecord template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasRecord(template.getRecord());
        }
    }

    public static GetRecordMatcher aGetRecordThat() {
        return new GetRecordMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static GetRecordMatcher aGetRecordLike(final GetRecord template) {
        return new GetRecordMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public GetRecordMatcher hasRecord(final Record record) {
        return hasRecord(equalTo(record));
    }

    public GetRecordMatcher hasRecord(final Matcher<? super Record> recordMatcher) {
        this.recordMatcher.setMatcher(recordMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final GetRecord item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
