package com.blackiceinc.era.persistence.erau.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "ERA_CONFIG_FILE")
public class ConfigFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="era_config_file_seq_gen")
    @SequenceGenerator(name="era_config_file_seq_gen", sequenceName="ERA_CONFIG_FILE_SEQ")
    private Long id;

    @NotNull
    @Column(name = "FILE_NAME")
    private String fileName;

    @JsonIgnore
    @NotNull
    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "STATUS")
    private String status;

    @JsonIgnore
    @Column(name = "CREATED")
    private Timestamp created;

    @JsonIgnore
    @Column(name = "MODIFIED")
    private Timestamp modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "ConfigFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
