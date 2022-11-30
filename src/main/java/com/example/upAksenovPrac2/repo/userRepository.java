package com.example.upAksenovPrac2.repo;

import com.example.upAksenovPrac2.models.user;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<user,Long> {
    user findUserByUsername(String username);
}
