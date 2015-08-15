/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.util;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class PartialList<T> extends AbstractCollection<T> {
    public static <T> PartialList<T> middleIncomplete(Collection<T> items, long total, long offset) {
        return new PartialList<T>(items, total, offset, Type.INCOMPLETE, true);
    }
    public static <T> PartialList<T> lastIncomplete(Collection<T> items, long total, long offset) {
        return new PartialList<T>(items, total, offset, Type.INCOMPLETE, false);
    }

    public static <T> PartialList<T> complete(Collection<T> items, long total, long offset) {
        return new PartialList<T>(items, total, offset, Type.COMPLETE, false);
    }

    public static <T> PartialList<T> empty() {
        return new PartialList<T>(Collections.<T>emptyList(), 0, 0, Type.COMPLETE, false);
    }

    private final Collection<T> items;
    private final long total;
    private final long offset;
    private final Type type;
    private final boolean hasMore;

    private PartialList(Collection<T> items, long total, long offset, Type type, boolean hasMore) {
        this.items = items;
        this.total = total;
        this.offset = offset;
        this.type = type;
        this.hasMore = hasMore;
    }

    public Collection<T> getItems() {
        return items;
    }

    public long getTotal() {
        return total;
    }

    public long getOffset() {
        return offset;
    }

    public Type getType() {
        return type;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    @Override
    public int size() {
        return items.size();
    }

    public enum Type {
        COMPLETE,
        INCOMPLETE
    }
}
