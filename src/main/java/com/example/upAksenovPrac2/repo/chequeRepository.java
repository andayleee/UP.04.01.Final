package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.cheque;
import org.springframework.data.repository.CrudRepository;

public interface chequeRepository extends CrudRepository<cheque, Long> {
    cheque findByEmployeeFIO(String employeeFIO);
}
