package com.blackiceinc.era.services.exception;

import com.blackiceinc.era.services.exception.model.CfgMessage;

import java.io.FileNotFoundException;
import java.util.List;

public class CfgImportExportException extends ServiceException {

    private List<CfgMessage> errors;

    public CfgImportExportException(Throwable cause) {
        super(cause);
    }

    public CfgImportExportException(List<CfgMessage> errors) {
        super();
        this.errors = errors;
    }

    public CfgImportExportException(String message, Throwable ex) {
        super(message, ex);
    }

    public List<CfgMessage> getErrors() {
        return errors;
    }
}
