package com.example.upAksenovPrac2.models;

import javax.persistence.*;
@Entity
@Table(name = "cheque")
public class cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeFIO;
    @OneToOne(optional = true, mappedBy = "cheque")
    private ticket owner;

    public cheque(String employeeFIO) {
        this.employeeFIO = employeeFIO;
    }
    public cheque (){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeFIO() {
        return employeeFIO;
    }

    public void setEmployeeFIO(String employeeFIO) {
        this.employeeFIO = employeeFIO;
    }

    public ticket getOwner() {
        return owner;
    }

    public void setOwner(ticket owner) {
        this.owner = owner;
    }
}
