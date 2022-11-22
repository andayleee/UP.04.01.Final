package com.example.upAksenovPrac2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class flight {
    public flight(Date dateOfFlight, Time timeOfDeparture, String pointOfDeparture, boolean soldOut, int countOfSeats) {
        this.dateOfFlight = dateOfFlight;
        this.timeOfDeparture = timeOfDeparture;
        this.pointOfDeparture = pointOfDeparture;
        this.soldOut = soldOut;
        this.countOfSeats = countOfSeats;
    }
    public flight(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateOfFlight;
    private Time timeOfDeparture;
    private  String pointOfDeparture;
    private boolean soldOut;
    private int countOfSeats;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(Date dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public Time getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(Time timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getCountOfSeats() {
        return countOfSeats;
    }

    public void setCountOfSeats(int countOfSeats) {
        this.countOfSeats = countOfSeats;
    }
}
