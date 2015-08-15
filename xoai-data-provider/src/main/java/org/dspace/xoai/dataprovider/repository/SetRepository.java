/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.xoai.dataprovider.repository;

import org.dspace.xoai.dataprovider.util.PartialList;
import org.dspace.xoai.model.oaipmh.Set;

public interface SetRepository {
    public boolean supportSets();
    public PartialList<Set> retrieveSets(int offset, int length);
    public boolean exists(String setSpec);
}
