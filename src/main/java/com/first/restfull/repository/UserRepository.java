package com.first.restfull.repository;

import com.first.restfull.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String name);
}
