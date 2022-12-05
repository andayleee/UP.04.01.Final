package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "cheque")
public class cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ведите все данные")
    private Date dateOfForm;
    @NotNull(message = "Ведите все данные")
    private String link;
    @OneToOne(optional = true, mappedBy = "cheque",  cascade = CascadeType.ALL)
    private ticket owner;

    public cheque(Date dateOfForm, String link) {
        this.dateOfForm = dateOfForm;
        this.link = link;
    }
    public cheque (){}
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ticket getOwner() {
        return owner;
    }

    public void setOwner(ticket owner) {
        this.owner = owner;
    }
}
