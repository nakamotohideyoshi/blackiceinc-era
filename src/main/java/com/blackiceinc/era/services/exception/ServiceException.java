package com.blackiceinc.era.services.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 4909469928851022657L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
