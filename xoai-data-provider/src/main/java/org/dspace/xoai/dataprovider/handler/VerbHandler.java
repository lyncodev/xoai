/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handler;

import org.dspace.xoai.dataprovider.request.VerbRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.model.oaipmh.OAIPMHContent;

public interface VerbHandler<Request extends VerbRequest, Response extends OAIPMHContent> {
    ErrorOrResponse<Error, Response> handle (Request request);

    public enum Error {
        CANNOT_DISSEMINATE_WITH_FORMAT,
        NO_METADATA_FORMATS,
        SETS_NOT_SUPPORTED,
        ITEM_DOES_NOT_EXIST
    }
}
