package com.first.restfull.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.first.restfull.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setSecondName(user.getSecondName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}