package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userSur;
    private String userNamee;
    private String userPatr;
    private String username;
    private String password;
    private boolean active;
    @OneToMany(mappedBy = "userNa", fetch = FetchType.EAGER)
    private Collection<ticket> tenants;

    @ElementCollection(targetClass = role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<role> roles;

    public user() {
    }

    public user(String userSur, String userNamee, String userPatr, String username, String password, boolean active, Set<role> roles) {
        this.userSur = userSur;
        this.userNamee = userNamee;
        this.userPatr = userPatr;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserSur() {
        return userSur;
    }

    public void setUserSur(String userSur) {
        this.userSur = userSur;
    }

    public String getUserNamee() {
        return userNamee;
    }

    public void setUserNamee(String userNamee) {
        this.userNamee = userNamee;
    }

    public String getUserPatr() {
        return userPatr;
    }

    public void setUserPatr(String userPatr) {
        this.userPatr = userPatr;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<role> getRoles() {
        return roles;
    }

    public void setRoles(Set<role> roles) {
        this.roles = roles;
    }

    public Collection<ticket> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<ticket> tenants) {
        this.tenants = tenants;
    }
}
