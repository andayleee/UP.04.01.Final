package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class financialReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Ведите все данные")
    private Date dateOfForm;
    @NotNull(message = "Ведите все данные")
    private double totalIncome;
    @NotNull(message = "Ведите все данные")
    private double totalPayments;
    @NotNull(message = "Ведите все данные")
    @ManyToOne(optional = true)
    private user userNa;
    @NotNull(message = "Ведите все данные")
    @ManyToOne(optional = true)
    private coAccount nameOfBa;

    public financialReport(Date dateOfForm, double totalIncome, double totalPayments, user userNa, coAccount nameOfBa) {
        this.dateOfForm = dateOfForm;
        this.totalIncome = totalIncome;
        this.totalPayments = totalPayments;
        this.userNa = userNa;
        this.nameOfBa = nameOfBa;
    }

    public financialReport(){}

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

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(double totalPayments) {
        this.totalPayments = totalPayments;
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
}
