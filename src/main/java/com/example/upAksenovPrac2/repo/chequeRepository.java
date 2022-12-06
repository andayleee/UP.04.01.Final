package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.cheque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface chequeRepository extends CrudRepository<cheque, Long> {
    cheque findByLink(String link);

    @Query(value="SELECT cheque.id, date_of_form, link FROM ticket inner JOIN cheque on cheque_id=cheque.id WHERE cheque.id = cheque_id",nativeQuery = true)
    List<cheque> aaa();

    @Query(value="SELECT * FROM cheque ",nativeQuery = true)
    List<cheque> bbb();
}
