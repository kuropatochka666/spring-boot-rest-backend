package com.first.restfull.security;

import com.first.restfull.model.User;
import com.first.restfull.security.jwt.JwtUser;
import com.first.restfull.security.jwt.JwtUserFactory;
import com.first.restfull.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User with username: " +username+ " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
