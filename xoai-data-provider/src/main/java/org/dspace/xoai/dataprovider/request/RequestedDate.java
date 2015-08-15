/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.request;

import java.util.Date;

public class RequestedDate {
    private final Date date;

    public RequestedDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
