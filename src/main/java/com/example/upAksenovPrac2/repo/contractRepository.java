package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.contract;
import org.springframework.data.repository.CrudRepository;

public interface contractRepository extends CrudRepository<contract, Long> {
    contract findByCarrierCoName(String carrierCoName);
}