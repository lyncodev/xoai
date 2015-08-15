/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.repository;

import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.model.oaipmh.Header;
import org.dspace.xoai.model.oaipmh.Record;
import org.dspace.xoai.model.oaipmh.ResumptionToken;
import org.dspace.xoai.model.oaipmh.Set;

public interface ResumptionTokenResolver {
    ResumptionToken resolveForRecords(PartialList<Record> items);
    ResumptionToken resolveForSets(PartialList<Set> items);
    ResumptionToken resolveForIdentifiers(PartialList<Header> items);
}
