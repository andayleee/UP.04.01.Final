package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.coAccount;
import org.springframework.data.repository.CrudRepository;

public interface coAccountRepository extends CrudRepository<coAccount, Long> {
    coAccount findByNameOfBank(String nameOfBank);
}
