package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
public class ticketList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Ведите все данные")
    private Date dateOfForm;
    @OneToMany(mappedBy = "ticketLi", fetch = FetchType.EAGER)
    private Collection<ticket> tenants;

    public ticketList(Date dateOfForm) {
        this.dateOfForm = dateOfForm;
    }

    public ticketList(){}

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

    public Collection<ticket> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<ticket> tenants) {
        this.tenants = tenants;
    }

}
