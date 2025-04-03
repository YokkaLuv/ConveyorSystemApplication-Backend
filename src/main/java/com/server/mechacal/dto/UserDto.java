package com.server.mechacal.dto;


import org.springframework.beans.factory.annotation.Value;
import com.server.mechacal.auth.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String userId;

    private String name;

    private String phoneNumber;

    @Value("${base.url}")
    private String imageUrl;

    private String email;

    public void update(User user) {
        if (user.getUserId() != null) userId = user.getUserId();
        if (user.getName() != null) name = user.getName();
        if (user.getPhoneNumber() != null) phoneNumber = user.getPhoneNumber();
        if (user.getEmail() != null) email = user.getEmail();
        if (user.getImageName() != null) imageUrl = "/file/" + user.getImageName();
        
    }
}