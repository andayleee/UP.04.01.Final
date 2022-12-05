package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.ticketList;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface ticketListRepository extends CrudRepository<ticketList, Long> {
    ticketList findByDateOfForm(Date dateOfForm);
}
