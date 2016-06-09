package com.blackiceinc.era.services.exception;


import com.blackiceinc.era.services.exception.model.CfgMessage;
import org.hibernate.HibernateException;

public class CfgImportException extends ServiceException {

    private static final long serialVersionUID = -1639408744859336373L;
    private CfgMessage cfgMessage;

    public CfgImportException(String message) {
        super(message);
    }

    public CfgImportException(CfgMessage cfgMessage) {
        super(cfgMessage.getMsg());
        this.cfgMessage = cfgMessage;
    }

    public CfgImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public CfgImportException(CfgMessage cfgMessage, Exception ex) {
        super(cfgMessage.getMsg(), ex);
        this.cfgMessage = cfgMessage;
    }

    public CfgMessage getCfgMessage() {
        return cfgMessage;
    }


}
