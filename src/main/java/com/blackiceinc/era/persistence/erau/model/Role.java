package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERA_ROLE")
public class Role {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_DASHBOARD = "ROLE_DASHBOARD";
    public static final String ROLE_CONFIGURATION = "ROLE_CONFIGURATION";
    public static final String ROLE_DATA = "ROLE_DATA";

    @Id
    private String name;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    public Role() {
    }

    public Role(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
