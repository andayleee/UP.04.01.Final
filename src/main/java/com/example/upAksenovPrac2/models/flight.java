package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class flight {
    public flight(Date dateOfFlight, Time timeOfDeparture, Time timeOfArrival, String pointOfDeparture,String pointOfArrival, double coast, contract carrierCo) {
        this.dateOfFlight = dateOfFlight;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.pointOfDeparture = pointOfDeparture;
        this.pointOfArrival = pointOfArrival;
        this.coast = coast;
        this.carrierCo = carrierCo;
    }
    public flight(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ведите дату")
    private Date dateOfFlight;
    @NotNull(message = "Ведите время отправления")
    private Time timeOfDeparture;
    @NotNull(message = "Ведите время прибытия")
    private Time timeOfArrival;
    @NotEmpty(message = "Значение не может быть пустым")
    @Size (max = 20, message = "Пункт отправления не должен привышать 20 символов")
    private  String pointOfDeparture;
    @NotEmpty(message = "Значение не может быть пустым")
    @Size (max = 20, message = "Пункт прибытия не должен привышать 20 символов")
    private  String pointOfArrival;
    @Min(value=0, message="Стоимость не может быть меньше 0")
    @Digits(integer = 6, fraction = 2, message = "Стоимость не может быть более 999,999.99")
    private double coast;
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

    public Time getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Time timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public String getPointOfArrival() {
        return pointOfArrival;
    }

    public void setPointOfArrival(String pointOfArrival) {
        this.pointOfArrival = pointOfArrival;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
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
