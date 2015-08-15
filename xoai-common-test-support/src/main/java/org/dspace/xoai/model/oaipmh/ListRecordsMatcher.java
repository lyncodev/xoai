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

@Matches(ListRecords.class)
public final class ListRecordsMatcher extends CompositePropertyMatcher<ListRecords> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a ListRecords";
    private final PropertyMatcher<Optional<ResumptionToken>> resumptionTokenMatcher = new ReflectingPropertyMatcher<Optional<ResumptionToken>>("resumptionToken", this);
    private final PropertyMatcher<List<Record>> recordsMatcher = new ReflectingPropertyMatcher<List<Record>>("records", this);

    private ListRecordsMatcher(final String matchedObjectDescription, final ListRecords template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasResumptionToken(template.getResumptionToken());
            hasRecords(template.getRecords());
        }
    }

    public static ListRecordsMatcher aListRecordsThat() {
        return new ListRecordsMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static ListRecordsMatcher aListRecordsLike(final ListRecords template) {
        return new ListRecordsMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public ListRecordsMatcher hasResumptionToken(final Optional<ResumptionToken> resumptionToken) {
        return hasResumptionToken(equalTo(resumptionToken));
    }

    public ListRecordsMatcher hasResumptionToken(final Matcher<? super Optional<ResumptionToken>> resumptionTokenMatcher) {
        this.resumptionTokenMatcher.setMatcher(resumptionTokenMatcher);
        return this;
    }

    public ListRecordsMatcher hasRecords(final List<Record> records) {
        return hasRecords(equalTo(records));
    }

    public ListRecordsMatcher hasRecords(final Matcher<? super List<Record>> recordsMatcher) {
        this.recordsMatcher.setMatcher(recordsMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final ListRecords item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
