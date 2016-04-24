package com.blackiceinc.era.services.exception;


public class ExportConfigurationException extends ServiceException{

    private static final long serialVersionUID = 6716447131521418191L;

    public ExportConfigurationException(String message) {
        super(message);
    }

    public ExportConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
