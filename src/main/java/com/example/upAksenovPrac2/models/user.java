package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size (max = 20, message = "Фамилия не должна привышать 20 символов")
    private String userSur;
    @Size (max = 20, message = "Имя не должно привышать 20 символов")
    private String userNamee;
    @Size (max = 20, message = "Отчество не должно привышать 20 символов")
    private String userPatr;
    @Size (max = 30, message = "Логин не должен привышать 20 символов")
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
