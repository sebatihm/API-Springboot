package com.springbook.application.sjhm.API_springboot.Model.Web.DTO;


import java.util.UUID;
import com.springbook.application.sjhm.API_springboot.Model.User.User;

public record UserDTO(UUID userId, String email, UUID authServerId, String mobileToken) {
    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId().getId(),
                user.getEmail(),
                user.getAuthServerId().value(),
                user.getMobileToken());
    }
}
