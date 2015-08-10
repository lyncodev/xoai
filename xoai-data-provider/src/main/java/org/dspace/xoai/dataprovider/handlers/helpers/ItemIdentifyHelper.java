/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handlers.helpers;

import org.dspace.xoai.dataprovider.model.ItemIdentifier;

public class ItemIdentifyHelper {
    private ItemIdentifier item;

    public ItemIdentifyHelper(ItemIdentifier item) {
        this.item = item;
    }

    public ItemIdentifier getItem() {
        return item;
    }
}
