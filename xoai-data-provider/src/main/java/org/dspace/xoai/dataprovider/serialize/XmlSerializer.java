/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize;

import org.dspace.xoai.dataprovider.xml.XmlWriter;

public interface XmlSerializer<T> {
    void serialize(XmlWriter xmlWriter, T input);
}
