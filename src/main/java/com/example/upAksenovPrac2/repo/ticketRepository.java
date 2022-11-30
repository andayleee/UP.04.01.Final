package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ticketRepository extends CrudRepository<ticket, Long> {

    List<ticket> findByFioClientContains(String FioClient);
    List<ticket> findByFioClientEquals(String FioClient);
}
