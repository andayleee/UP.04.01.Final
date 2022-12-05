package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class taxReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOfForm;
    private double ammountOfTax;
    @ManyToOne(optional = true)
    private user userNa;
    @ManyToOne(optional = true)
    private coAccount nameOfBa;
    @ManyToOne(optional = true)
    private requisites req;

    public taxReturn(Date dateOfForm, double ammountOfTax, user userNa, coAccount nameOfBa, requisites req) {
        this.dateOfForm = dateOfForm;
        this.ammountOfTax = ammountOfTax;
        this.userNa = userNa;
        this.nameOfBa = nameOfBa;
        this.req = req;
    }
    public taxReturn(){}

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

    public double getAmmountOfTax() {
        return ammountOfTax;
    }

    public void setAmmountOfTax(double ammountOfTax) {
        this.ammountOfTax = ammountOfTax;
    }

    public user getUserNa() {
        return userNa;
    }

    public void setUserNa(user userNa) {
        this.userNa = userNa;
    }

    public coAccount getNameOfBa() {
        return nameOfBa;
    }

    public void setNameOfBa(coAccount nameOfBa) {
        this.nameOfBa = nameOfBa;
    }

    public requisites getReq() {
        return req;
    }

    public void setReq(requisites req) {
        this.req = req;
    }
}
