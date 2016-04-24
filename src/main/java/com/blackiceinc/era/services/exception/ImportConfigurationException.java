package com.blackiceinc.era.services.exception;


public class ImportConfigurationException extends ServiceException{

    private static final long serialVersionUID = -1639408744859336373L;

    public ImportConfigurationException(String message) {
        super(message);
    }

    public ImportConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
