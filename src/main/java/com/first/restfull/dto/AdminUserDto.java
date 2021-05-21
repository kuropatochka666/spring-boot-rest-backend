package com.first.restfull.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.first.restfull.model.Status;
import com.first.restfull.model.User;
import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String status;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setSecondName(user.getSecondName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setStatus(user.getStatus().name());
        return adminUserDto;
    }
}