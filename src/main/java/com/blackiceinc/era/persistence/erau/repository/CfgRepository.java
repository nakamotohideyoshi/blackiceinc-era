package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;

public abstract class CfgRepository {
    public abstract void deleteAll();

    public abstract void insert(CfgObject cfgObject);
}
