package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
public class contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOfForm;
    private String carrierCoName;
    @OneToMany(mappedBy = "carrierCo", fetch = FetchType.EAGER)
    private Collection<flight> tenants;

    public contract(Date dateOfForm, String carrierCoName) {
        this.dateOfForm = dateOfForm;
        this.carrierCoName = carrierCoName;
    }

    public contract(){}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Date getDateOfForm() {
        return dateOfForm;
    }

    public void setDateOfForm(Date dateOfForm) {
        this.dateOfForm = dateOfForm;
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
