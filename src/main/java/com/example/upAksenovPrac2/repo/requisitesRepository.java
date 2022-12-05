package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.coAccount;
import com.example.upAksenovPrac2.models.requisites;
import org.springframework.data.repository.CrudRepository;

public interface requisitesRepository extends CrudRepository<requisites, Long> {
    requisites findByINN(String INN);
}
