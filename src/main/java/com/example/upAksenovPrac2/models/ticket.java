package com.example.upAksenovPrac2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class ticket {
    public ticket(String fioClient, Date dateOfDelivery, double coast, int countClientsSeats, boolean isPaid) {
        this.fioClient = fioClient;
        this.dateOfDelivery = dateOfDelivery;
        this.coast = coast;
        this.countClientsSeats = countClientsSeats;
        this.isPaid = isPaid;
    }
    public ticket(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Значение не может быть пустым")
    private String fioClient;
    @NotNull(message = "Ведите дату")
    private Date dateOfDelivery;
    @Digits(integer = 6, fraction = 2, message = "Число не может быть более 999999,99")
    private  double coast;
    @Min(value=0, message="Количество купленных мест не может быть меньше 0")
    @Max(value=10, message="Количество купленных мест не может быть больше 10")
    private int countClientsSeats;
    private boolean isPaid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFioClient() {
        return fioClient;
    }

    public void setFioClient(String fioClient) {
        this.fioClient = fioClient;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public int getCountClientsSeats() {
        return countClientsSeats;
    }

    public void setCountClientsSeats(int countClientsSeats) {
        this.countClientsSeats = countClientsSeats;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
