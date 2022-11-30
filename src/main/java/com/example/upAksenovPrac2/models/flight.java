package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class flight {
    public flight(Date dateOfFlight, Time timeOfDeparture, String pointOfDeparture, boolean soldOut, int countOfSeats, contract carrierCo) {
        this.dateOfFlight = dateOfFlight;
        this.timeOfDeparture = timeOfDeparture;
        this.pointOfDeparture = pointOfDeparture;
        this.soldOut = soldOut;
        this.countOfSeats = countOfSeats;
        this.carrierCo = carrierCo;
    }
    public flight(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ведите дату")
    private Date dateOfFlight;
    @NotNull(message = "Ведите время")
    private Time timeOfDeparture;
    @NotEmpty(message = "Значение не может быть пустым")
    private  String pointOfDeparture;
    private boolean soldOut;
    @Min(value=0, message="Количество мест не может быть меньше 0")
    @Max(value=10, message="Количество мест не может быть больше 10")
    private int countOfSeats;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private contract carrierCo;

    @ManyToMany
    @JoinTable (name="flight_seat",
            joinColumns=@JoinColumn (name="flight_id"),
            inverseJoinColumns=@JoinColumn(name="seat_id"))
    private List<seat> seats;

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

    public contract getCarrierCo() {
        return carrierCo;
    }

    public void setCarrierCo(contract carrierCo) {
        this.carrierCo = carrierCo;
    }

    public List<seat> getSeats() {
        return seats;
    }

    public void setSeats(List<seat> seats) {
        this.seats = seats;
    }
}
