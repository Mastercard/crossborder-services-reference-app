package com.mastercard.crossborder.api.exception;

import com.mastercard.crossborder.api.rest.response.Errors;
public class ServiceException extends Exception{

    private final Errors errors = new Errors();
    public ServiceException(String errorMessage)
    {
        super(errorMessage);
    }
    public ServiceException(String errorMessage, Errors serviceErrors)
    {
        super(errorMessage);
        errors.setError(serviceErrors.getError());
    }

    public Errors getErrors() {
        return errors;
    }

}