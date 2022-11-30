package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carrierCoName;

    public contract( String carrierCoName) {
        this.carrierCoName = carrierCoName;
    }

    public contract(){}

    @OneToMany(mappedBy = "carrierCo", fetch = FetchType.EAGER)
    private Collection<flight> tenants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierCoName() {
        return carrierCoName;
    }

    public void setCarrierCoName(String carrierCoName) {
        this.carrierCoName = carrierCoName;
    }

    public Collection<flight> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<flight> tenants) {
        this.tenants = tenants;
    }
}
