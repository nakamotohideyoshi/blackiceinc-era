package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ERA_CONFIG_APP")
public class ConfigApp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "era_config_app_seq_gen")
    @SequenceGenerator(name = "era_config_app_seq_gen", sequenceName = "ERA_CONFIG_APP_SEQ")
    private Long id;

    @NotNull
    @Column(name = "KEY")
    private String key;

    @NotNull
    @Column(name = "VALUE")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
