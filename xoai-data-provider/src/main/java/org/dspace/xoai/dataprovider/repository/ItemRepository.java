/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.xoai.dataprovider.repository;

import org.dspace.xoai.dataprovider.request.ListIdentifiersRequest;
import org.dspace.xoai.dataprovider.request.ListRecordsRequest;
import org.dspace.xoai.dataprovider.util.ErrorOrResponse;
import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.Record;

/**
 * This class wraps the data source of items.
 *
 * @author Development @ Lyncode
 * @version 3.1.0
 */
public interface ItemRepository {

    PartialList<Header> listIdentifiers (ListIdentifiersRequest request);
    PartialList<Record> listRecords (ListRecordsRequest request);
    ErrorOrResponse<Error, Record> resolve (String identifier, String metadataPrefix);
    boolean exists (String identifier);

    public enum Error {
        CANNOT_DISSEMINATE_FORMAT,
        ID_DOES_NOT_EXIST
    }
}
