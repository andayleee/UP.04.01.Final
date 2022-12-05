package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class ticket {

    public ticket(Date dateOfBooking, Date dateOfEndBooking, int countClientsSeats, ticketList ticketLi, user userNa) {
        this.dateOfBooking = dateOfBooking;
        this.dateOfEndBooking = dateOfEndBooking;
        this.countClientsSeats = countClientsSeats;
        this.ticketLi = ticketLi;
        this.userNa = userNa;
    }
    public ticket(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ведите дату")
    private Date dateOfBooking;
    @NotNull(message = "Ведите дату")
    private Date dateOfEndBooking;
    @Min(value=0, message="Количество купленных мест не может быть меньше 0")
    @Max(value=25, message="Количество купленных мест не может быть больше 25")
    private int countClientsSeats;
    @ManyToOne(optional = true)
    private ticketList ticketLi;

    @ManyToOne(optional = true)
    private user userNa;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="cheque_id")
    private cheque cheque;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Date getDateOfEndBooking() {
        return dateOfEndBooking;
    }

    public void setDateOfEndBooking(Date dateOfEndBooking) {
        this.dateOfEndBooking = dateOfEndBooking;
    }

    public int getCountClientsSeats() {
        return countClientsSeats;
    }

    public void setCountClientsSeats(int countClientsSeats) {
        this.countClientsSeats = countClientsSeats;
    }

    public ticketList getTicketLi() {
        return ticketLi;
    }

    public void setTicketLi(ticketList ticketLi) {
        this.ticketLi = ticketLi;
    }

    public user getUserNa() {
        return userNa;
    }

    public void setUserNa(user userNa) {
        this.userNa = userNa;
    }

    public cheque getCheck() {
        return cheque;
    }

    public void setCheck(cheque cheque) {
        this.cheque = cheque;
    }
}
