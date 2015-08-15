package org.dspace.xoai.model.oaipmh;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(ListMetadataFormats.class)
public final class ListMetadataFormatsMatcher extends CompositePropertyMatcher<ListMetadataFormats> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a ListMetadataFormats";
    private final PropertyMatcher<List<MetadataFormat>> metadataFormatsMatcher = new ReflectingPropertyMatcher<List<MetadataFormat>>("metadataFormats", this);

    private ListMetadataFormatsMatcher(final String matchedObjectDescription, final ListMetadataFormats template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasMetadataFormats(template.getMetadataFormats());
        }
    }

    public static ListMetadataFormatsMatcher aListMetadataFormatsThat() {
        return new ListMetadataFormatsMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static ListMetadataFormatsMatcher aListMetadataFormatsLike(final ListMetadataFormats template) {
        return new ListMetadataFormatsMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public ListMetadataFormatsMatcher hasMetadataFormats(final List<MetadataFormat> metadataFormats) {
        return hasMetadataFormats(equalTo(metadataFormats));
    }

    public ListMetadataFormatsMatcher hasMetadataFormats(final Matcher<? super List<MetadataFormat>> metadataFormatsMatcher) {
        this.metadataFormatsMatcher.setMatcher(metadataFormatsMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final ListMetadataFormats item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
