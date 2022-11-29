package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.seat;
import org.springframework.data.repository.CrudRepository;

public interface seatRepository extends CrudRepository<seat, Long> {
    seat findByPlace(String place);
}
