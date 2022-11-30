package com.example.upAksenovPrac2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String place;
    @ManyToMany
    @JoinTable(name="flight_seat",
            joinColumns=@JoinColumn(name="seat_id"),
            inverseJoinColumns=@JoinColumn(name="flight_id"))
    private List<flight> fly;

    public seat(String place) {
        this.place = place;
    }
    public seat(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return place;
    }

    public void setName(String place) {
        this.place = place;
    }

    public List<flight> getFly() {
        return fly;
    }

    public void setStudents(List<flight> fly) {
        this.fly = fly;
    }

}
