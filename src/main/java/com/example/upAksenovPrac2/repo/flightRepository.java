package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.flight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface flightRepository extends CrudRepository<flight, Long> {

    List<flight> findByPointOfDepartureContainsOrPointOfArrivalContains(String pointOfDeparture, String pointOfArrival);
}