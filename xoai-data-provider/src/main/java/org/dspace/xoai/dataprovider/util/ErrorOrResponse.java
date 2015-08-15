/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.util;

import com.google.common.base.Optional;

public class ErrorOrResponse<Error, Ok> {
    public static <E, O> ErrorOrResponse<E, O> error (E error) {
        return new ErrorOrResponse<E, O>(Optional.of(error), Optional.<O>absent());
    }
    public static <E, O> ErrorOrResponse<E, O> response(O ok) {
        return new ErrorOrResponse<E, O>(Optional.<E>absent(), Optional.of(ok));
    }

    private final Optional<Error> error;
    private final Optional<Ok> ok;

    private ErrorOrResponse(Optional<Error> error, Optional<Ok> ok) {
        this.error = error;
        this.ok = ok;
    }

    public boolean isOk () {
        return ok.isPresent();
    }

    public Error getError () {
        return error.get();
    }

    public Ok getResponse() {
        return ok.get();
    }
}
