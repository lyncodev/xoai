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

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(Identify.class)
public final class IdentifyMatcher extends CompositePropertyMatcher<Identify> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "an Identify";
    private final PropertyMatcher<List<String>> adminEmailsMatcher = new ReflectingPropertyMatcher<List<String>>("adminEmails", this);
    private final PropertyMatcher<String> protocolVersionMatcher = new ReflectingPropertyMatcher<String>("protocolVersion", this);
    private final PropertyMatcher<List<String>> compressionsMatcher = new ReflectingPropertyMatcher<List<String>>("compressions", this);
    private final PropertyMatcher<Granularity> granularityMatcher = new ReflectingPropertyMatcher<Granularity>("granularity", this);
    private final PropertyMatcher<String> baseURLMatcher = new ReflectingPropertyMatcher<String>("baseURL", this);
    private final PropertyMatcher<String> repositoryNameMatcher = new ReflectingPropertyMatcher<String>("repositoryName", this);
    private final PropertyMatcher<List<Description>> descriptionsMatcher = new ReflectingPropertyMatcher<List<Description>>("descriptions", this);
    private final PropertyMatcher<DeletedRecord> deletedRecordMatcher = new ReflectingPropertyMatcher<DeletedRecord>("deletedRecord", this);
    private final PropertyMatcher<Date> earliestDatestampMatcher = new ReflectingPropertyMatcher<Date>("earliestDatestamp", this);

    private IdentifyMatcher(final String matchedObjectDescription, final Identify template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasAdminEmails(template.getAdminEmails());
            hasProtocolVersion(template.getProtocolVersion());
            hasCompressions(template.getCompressions());
            hasGranularity(template.getGranularity());
            hasBaseURL(template.getBaseURL());
            hasRepositoryName(template.getRepositoryName());
            hasDescriptions(template.getDescriptions());
            hasDeletedRecord(template.getDeletedRecord());
            hasEarliestDatestamp(template.getEarliestDatestamp());
        }
    }

    public static IdentifyMatcher anIdentifyThat() {
        return new IdentifyMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static IdentifyMatcher anIdentifyLike(final Identify template) {
        return new IdentifyMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public IdentifyMatcher hasAdminEmails(final List<String> adminEmails) {
        return hasAdminEmails(equalTo(adminEmails));
    }

    public IdentifyMatcher hasAdminEmails(final Matcher<? super List<String>> adminEmailsMatcher) {
        this.adminEmailsMatcher.setMatcher(adminEmailsMatcher);
        return this;
    }

    public IdentifyMatcher hasProtocolVersion(final String protocolVersion) {
        return hasProtocolVersion(equalTo(protocolVersion));
    }

    public IdentifyMatcher hasProtocolVersion(final Matcher<? super String> protocolVersionMatcher) {
        this.protocolVersionMatcher.setMatcher(protocolVersionMatcher);
        return this;
    }

    public IdentifyMatcher hasCompressions(final List<String> compressions) {
        return hasCompressions(equalTo(compressions));
    }

    public IdentifyMatcher hasCompressions(final Matcher<? super List<String>> compressionsMatcher) {
        this.compressionsMatcher.setMatcher(compressionsMatcher);
        return this;
    }

    public IdentifyMatcher hasGranularity(final Granularity granularity) {
        return hasGranularity(equalTo(granularity));
    }

    public IdentifyMatcher hasGranularity(final Matcher<? super Granularity> granularityMatcher) {
        this.granularityMatcher.setMatcher(granularityMatcher);
        return this;
    }

    public IdentifyMatcher hasBaseURL(final String baseURL) {
        return hasBaseURL(equalTo(baseURL));
    }

    public IdentifyMatcher hasBaseURL(final Matcher<? super String> baseURLMatcher) {
        this.baseURLMatcher.setMatcher(baseURLMatcher);
        return this;
    }

    public IdentifyMatcher hasRepositoryName(final String repositoryName) {
        return hasRepositoryName(equalTo(repositoryName));
    }

    public IdentifyMatcher hasRepositoryName(final Matcher<? super String> repositoryNameMatcher) {
        this.repositoryNameMatcher.setMatcher(repositoryNameMatcher);
        return this;
    }

    public IdentifyMatcher hasDescriptions(final List<Description> descriptions) {
        return hasDescriptions(equalTo(descriptions));
    }

    public IdentifyMatcher hasDescriptions(final Matcher<? super List<Description>> descriptionsMatcher) {
        this.descriptionsMatcher.setMatcher(descriptionsMatcher);
        return this;
    }

    public IdentifyMatcher hasDeletedRecord(final DeletedRecord deletedRecord) {
        return hasDeletedRecord(equalTo(deletedRecord));
    }

    public IdentifyMatcher hasDeletedRecord(final Matcher<? super DeletedRecord> deletedRecordMatcher) {
        this.deletedRecordMatcher.setMatcher(deletedRecordMatcher);
        return this;
    }

    public IdentifyMatcher hasEarliestDatestamp(final Date earliestDatestamp) {
        return hasEarliestDatestamp(equalTo(earliestDatestamp));
    }

    public IdentifyMatcher hasEarliestDatestamp(final Matcher<? super Date> earliestDatestampMatcher) {
        this.earliestDatestampMatcher.setMatcher(earliestDatestampMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final Identify item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
