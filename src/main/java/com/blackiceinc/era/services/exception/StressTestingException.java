package com.blackiceinc.era.services.exception;

public class StressTestingException extends ServiceException {

    public StressTestingException(String message) {
        super(message);
    }

    public StressTestingException(String message, Throwable cause) {
        super(message, cause);
    }

    public StressTestingException(Throwable cause) {
        super(cause);
    }
}
