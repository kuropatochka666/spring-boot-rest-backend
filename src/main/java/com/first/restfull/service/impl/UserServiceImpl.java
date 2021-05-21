package com.first.restfull.service.impl;


import com.first.restfull.model.Status;
import lombok.extern.slf4j.Slf4j;
import com.first.restfull.model.Role;
import com.first.restfull.model.User;
import com.first.restfull.repository.RoleRepository;
import com.first.restfull.repository.UserRepository;
import com.first.restfull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);


        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();
        log.info("IN getAll - : {} users found", allUsers);
        return allUsers;
    }

    @Override
    public User findUsername(String username) {
        User findByName = userRepository.findByFirstName(username);
        log.info("IN findUsername - : {} user found by username: {}", findByName, username);
        return findByName;
    }

    @Override
    public User findById(Long id) {
        User findById = userRepository.findById(id).orElse(null);
        if (findById == null) {
            log.info("IN findById - : no user found by user id: {}", id);
            return null;
        }
        log.info("IN findById - : {} user found by user id: {}", findById, id);
        return findById;
    }

    @Override
    public void delete(Long id) {
    userRepository.deleteById(id);
    log.info("IN delete - user with id: {} deleted", id);
    }
}
