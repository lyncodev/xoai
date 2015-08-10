/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.handlers;

import com.lyncode.builder.Builder;
import org.dspace.xoai.dataprovider.builder.OAIRequestParametersBuilder;
import org.dspace.xoai.dataprovider.exceptions.HandlerException;
import org.dspace.xoai.dataprovider.exceptions.OAIException;
import org.dspace.xoai.dataprovider.model.DataProviderContext;
import org.dspace.xoai.dataprovider.parameters.OAICompiledRequest;
import org.dspace.xoai.dataprovider.parameters.OAIRequest;
import org.dspace.xoai.dataprovider.repository.Repository;
import org.dspace.xoai.exceptions.InvalidResumptionTokenException;
import org.dspace.xoai.xml.XmlWritable;


public abstract class VerbHandler<T extends XmlWritable> {
    private DataProviderContext dataProviderContext;
    private Repository repository;

    public VerbHandler (DataProviderContext dataProviderContext, Repository repository) {
        this.dataProviderContext = dataProviderContext;
        this.repository = repository;
    }

    public DataProviderContext getDataProviderContext() {
        return dataProviderContext;
    }

    public Repository getRepository() {
        return repository;
    }

    public T handle (OAIRequest parameters) throws HandlerException, InvalidResumptionTokenException, OAIException {
        return handle(parameters.compile());
    }

    public T handle (OAIRequestParametersBuilder parameters) throws OAIException, HandlerException, InvalidResumptionTokenException {
        return handle(parameters.build());
    }

    public T handle(Builder<OAICompiledRequest> parameters) throws OAIException, HandlerException {
        return handle(parameters.build());
    }

    public abstract T handle(OAICompiledRequest params) throws OAIException, HandlerException;
}
