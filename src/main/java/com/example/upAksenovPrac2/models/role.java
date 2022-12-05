package com.example.upAksenovPrac2.models;

import org.springframework.security.core.GrantedAuthority;

public enum role implements GrantedAuthority {
    USER, ADMIN, BUHGALTER, PURCHASES, SALES;


    @Override
    public String getAuthority() {
        return name();
    }
}
