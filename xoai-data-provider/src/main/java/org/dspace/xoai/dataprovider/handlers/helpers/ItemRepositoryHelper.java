/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handlers.helpers;

import org.dspace.xoai.dataprovider.exceptions.CannotDisseminateFormatException;
import org.dspace.xoai.dataprovider.exceptions.IdDoesNotExistException;
import org.dspace.xoai.dataprovider.exceptions.OAIException;
import org.dspace.xoai.dataprovider.filter.Scope;
import org.dspace.xoai.dataprovider.filter.ScopedFilter;
import org.dspace.xoai.dataprovider.handlers.results.ListItemIdentifiersResult;
import org.dspace.xoai.dataprovider.handlers.results.ListItemsResults;
import org.dspace.xoai.dataprovider.model.DataProviderContext;
import org.dspace.xoai.dataprovider.model.Item;
import org.dspace.xoai.dataprovider.model.MetadataFormat;
import org.dspace.xoai.dataprovider.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemRepositoryHelper {
    private ItemRepository itemRepository;

    public ItemRepositoryHelper(ItemRepository itemRepository) {
        super();
        this.itemRepository = itemRepository;
    }

    public ListItemIdentifiersResult getItemIdentifiers(DataProviderContext dataProviderContext,
                                                        int offset, int length, String metadataPrefix)
            throws CannotDisseminateFormatException, OAIException {
        return itemRepository.getItemIdentifiers(getScopedFilters(dataProviderContext, metadataPrefix), offset, length);
    }

    public ListItemIdentifiersResult getItemIdentifiers(DataProviderContext dataProviderContext,
                                                        int offset, int length, String metadataPrefix, Date from)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        return itemRepository.getItemIdentifiers(filters, offset, length, from);
    }

    private List<ScopedFilter> getScopedFilters(DataProviderContext dataProviderContext, String metadataPrefix) throws CannotDisseminateFormatException {
        List<ScopedFilter> filters = new ArrayList<ScopedFilter>();
        if (dataProviderContext.hasCondition())
            filters.add(new ScopedFilter(dataProviderContext.getCondition(), Scope.Context));

        MetadataFormat metadataFormat = dataProviderContext.formatForPrefix(metadataPrefix);
        if (metadataFormat.hasCondition())
            filters.add(new ScopedFilter(metadataFormat.getCondition(), Scope.MetadataFormat));
        return filters;
    }

    public ListItemIdentifiersResult getItemIdentifiersUntil(
            DataProviderContext dataProviderContext, int offset, int length, String metadataPrefix,
            Date until) throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        return itemRepository.getItemIdentifiersUntil(filters, offset, length, until);
    }

    public ListItemIdentifiersResult getItemIdentifiers(DataProviderContext dataProviderContext,
                                                        int offset, int length, String metadataPrefix, Date from, Date until)
            throws CannotDisseminateFormatException, OAIException {
        return itemRepository.getItemIdentifiers(getScopedFilters(dataProviderContext, metadataPrefix), offset, length, from, until);
    }

    public ListItemIdentifiersResult getItemIdentifiers(DataProviderContext dataProviderContext,
                                                        int offset, int length, String metadataPrefix, String setSpec)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItemIdentifiers(filters, offset, length);
        } else
            return itemRepository.getItemIdentifiers(filters, offset, length, setSpec);
    }

    public ListItemIdentifiersResult getItemIdentifiers(DataProviderContext dataProviderContext,
                                                        int offset, int length, String metadataPrefix, String setSpec,
                                                        Date from) throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItemIdentifiers(filters, offset, length, from);
        } else
            return itemRepository.getItemIdentifiers(filters, offset, length, setSpec,
                    from);
    }

    public ListItemIdentifiersResult getItemIdentifiersUntil(
            DataProviderContext dataProviderContext, int offset, int length, String metadataPrefix,
            String setSpec, Date until) throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItemIdentifiersUntil(filters, offset, length, until);
        } else
            return itemRepository.getItemIdentifiersUntil(filters, offset, length,
                    setSpec, until);
    }

    public ListItemIdentifiersResult getItemIdentifiers(DataProviderContext dataProviderContext,
                                                        int offset, int length, String metadataPrefix, String setSpec,
                                                        Date from, Date until) throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository
                    .getItemIdentifiers(filters, offset, length, from, until);
        } else
            return itemRepository.getItemIdentifiers(filters, offset, length, setSpec,
                    from, until);
    }

    public ListItemsResults getItems(DataProviderContext dataProviderContext, int offset,
                                     int length, String metadataPrefix)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        return itemRepository.getItems(filters, offset, length);
    }

    public ListItemsResults getItems(DataProviderContext dataProviderContext, int offset,
                                     int length, String metadataPrefix, Date from)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        return itemRepository.getItems(filters, offset, length, from);
    }

    public ListItemsResults getItemsUntil(DataProviderContext dataProviderContext, int offset,
                                          int length, String metadataPrefix, Date until)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        return itemRepository.getItemsUntil(filters, offset, length, until);
    }

    public ListItemsResults getItems(DataProviderContext dataProviderContext, int offset,
                                     int length, String metadataPrefix, Date from, Date until)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        return itemRepository.getItems(filters, offset, length, from, until);
    }

    public ListItemsResults getItems(DataProviderContext dataProviderContext, int offset,
                                     int length, String metadataPrefix, String setSpec)
            throws CannotDisseminateFormatException, OAIException {

        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItems(filters, offset, length);
        } else
            return itemRepository.getItems(filters, offset, length, setSpec);
    }

    public ListItemsResults getItems(DataProviderContext dataProviderContext, int offset,
                                     int length, String metadataPrefix, String setSpec, Date from)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItems(filters, offset, length, from);
        } else
            return itemRepository.getItems(filters, offset, length, setSpec, from);
    }

    public ListItemsResults getItemsUntil(DataProviderContext dataProviderContext, int offset,
                                          int length, String metadataPrefix, String setSpec, Date until)
            throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItemsUntil(filters, offset, length, until);
        } else
            return itemRepository.getItemsUntil(filters, offset, length, setSpec, until);
    }

    public ListItemsResults getItems(DataProviderContext dataProviderContext, int offset,
                                     int length, String metadataPrefix, String setSpec, Date from,
                                     Date until) throws CannotDisseminateFormatException, OAIException {
        List<ScopedFilter> filters = getScopedFilters(dataProviderContext, metadataPrefix);
        if (dataProviderContext.isStaticSet(setSpec)) {
            filters.add(new ScopedFilter(dataProviderContext.getSet(setSpec).getCondition(), Scope.Set));
            return itemRepository.getItems(filters, offset, length, from, until);
        } else
            return itemRepository.getItems(filters, offset, length, setSpec, from, until);
    }

    public Item getItem(String identifier) throws IdDoesNotExistException, OAIException {
        return itemRepository.getItem(identifier);
    }
}
