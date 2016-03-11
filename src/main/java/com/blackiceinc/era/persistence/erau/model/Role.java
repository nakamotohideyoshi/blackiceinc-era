package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERA_ROLE")
public class Role {

    public static final String ADMINISTRATOR_GROUP = "Administrator Group";
    public static final String DASHBOARD_USER_GROUP = "Dashboard User Group";
    public static final String CONFIGURATION_USER_GROUP = "Configuration User Group";
    public static final String DATA_USER_GROUP = "Data User Group";

    @Id
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
