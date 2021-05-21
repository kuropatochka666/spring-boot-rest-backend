package com.first.restfull.service;

import com.first.restfull.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findUsername(String username);

    User findById(Long id);

    void delete(Long id);


}
