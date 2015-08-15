/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import com.google.common.base.Optional;
import org.dspace.xoai.dataprovider.repository.ItemRepository;
import org.dspace.xoai.dataprovider.repository.MetadataFormatRepository;
import org.dspace.xoai.dataprovider.request.ListMetadataFormatsRequest;
import org.dspace.xoai.dataprovider.request.RequestedIdentifier;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.support.ErrorOrResponseMatcher;
import org.dspace.xoai.model.oaipmh.ListMetadataFormats;
import org.dspace.xoai.model.oaipmh.MetadataFormat;
import org.junit.Test;

import java.util.Collections;

import static org.dspace.xoai.model.oaipmh.ListMetadataFormatsMatcher.aListMetadataFormatsThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListMetadataFormatsHandlerTest {
    private final MetadataFormatRepository metadataFormatRepository = mock(MetadataFormatRepository.class);
    private final ItemRepository itemResolver = mock(ItemRepository.class);
    private ListMetadataFormatsHandler underTest = new ListMetadataFormatsHandler(itemResolver, metadataFormatRepository);

    @Test
    public void handleWhenIdentifierNotExists() throws Exception {
        String identifier = "identifier";
        ListMetadataFormatsRequest request = new ListMetadataFormatsRequest(Optional.of(new RequestedIdentifier(identifier)));

        given(itemResolver.exists(identifier)).willReturn(false);

        ErrorOrResponse<VerbHandler.Error, ListMetadataFormats> result = underTest.handle(request);

        assertThat(result,
                ErrorOrResponseMatcher.<VerbHandler.Error, ListMetadataFormats>anErrorOrResponseThat()
                    .hasError(VerbHandler.Error.ITEM_DOES_NOT_EXIST)
        );
    }

    @Test
    public void handleWhenIdentifierExistsButNoMetadataFormat() throws Exception {
        String identifier = "identifier";
        ListMetadataFormatsRequest request = new ListMetadataFormatsRequest(Optional.of(new RequestedIdentifier(identifier)));

        given(itemResolver.exists(identifier)).willReturn(true);
        given(metadataFormatRepository.getMetadataFormatsFor(identifier)).willReturn(Collections.<MetadataFormat>emptyList());

        ErrorOrResponse<VerbHandler.Error, ListMetadataFormats> result = underTest.handle(request);

        assertThat(result,
                ErrorOrResponseMatcher.<VerbHandler.Error, ListMetadataFormats>anErrorOrResponseThat()
                    .hasError(VerbHandler.Error.NO_METADATA_FORMATS)
        );
    }

    @Test
    public void handleWhenIdentifierExistsAndMetadataFormat() throws Exception {
        String identifier = "identifier";
        ListMetadataFormatsRequest request = new ListMetadataFormatsRequest(Optional.of(new RequestedIdentifier(identifier)));

        MetadataFormat metadataFormat = mock(MetadataFormat.class);
        given(itemResolver.exists(identifier)).willReturn(true);
        given(metadataFormatRepository.getMetadataFormatsFor(identifier)).willReturn(Collections.singleton(metadataFormat));

        ErrorOrResponse<VerbHandler.Error, ListMetadataFormats> result = underTest.handle(request);

        assertThat(result,
                ErrorOrResponseMatcher.<VerbHandler.Error, ListMetadataFormats>anErrorOrResponseThat()
                    .hasResponse(aListMetadataFormatsThat().hasMetadataFormats(hasItem(metadataFormat)))
        );
    }

    @Test
    public void handleWhenMetadataFormatNotExists() throws Exception {
        ListMetadataFormatsRequest request = new ListMetadataFormatsRequest(Optional.<RequestedIdentifier>absent());

        given(metadataFormatRepository.getMetadataFormats()).willReturn(Collections.<MetadataFormat>emptyList());

        ErrorOrResponse<VerbHandler.Error, ListMetadataFormats> result = underTest.handle(request);

        assertThat(result,
                ErrorOrResponseMatcher.<VerbHandler.Error, ListMetadataFormats>anErrorOrResponseThat()
                    .hasError(VerbHandler.Error.NO_METADATA_FORMATS)
        );
    }

    @Test
    public void handleWhenMetadataFormatExists() throws Exception {
        ListMetadataFormatsRequest request = new ListMetadataFormatsRequest(Optional.<RequestedIdentifier>absent());

        MetadataFormat metadataFormat = mock(MetadataFormat.class);
        given(metadataFormatRepository.getMetadataFormats()).willReturn(Collections.singleton(metadataFormat));

        ErrorOrResponse<VerbHandler.Error, ListMetadataFormats> result = underTest.handle(request);

        assertThat(result,
                ErrorOrResponseMatcher.<VerbHandler.Error, ListMetadataFormats>anErrorOrResponseThat()
                    .hasResponse(aListMetadataFormatsThat().hasMetadataFormats(hasItem(metadataFormat)))
        );
    }
}