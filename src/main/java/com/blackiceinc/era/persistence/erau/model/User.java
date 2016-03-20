package com.blackiceinc.era.persistence.erau.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "ERA_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "era_user_seq_gen")
    @SequenceGenerator(name = "era_user_seq_gen", sequenceName = "ERA_USER_SEQ")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CREATED")
    private Timestamp created;

    @Column(name = "MODIFIED")
    private Timestamp modified;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ERA_USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getRole() {
        if (roles != null) {
            Iterator<Role> iterator = roles.iterator();
            if (iterator.hasNext()) {
                return iterator.next().getName();
            }
        }
        return null;
    }

    public String getRoleDisplayName() {
        if (roles != null) {
            Iterator<Role> iterator = roles.iterator();
            if (iterator.hasNext()) {
                return iterator.next().getDisplayName();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
