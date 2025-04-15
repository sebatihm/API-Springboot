package com.springbook.application.sjhm.API_springboot.Model.User.Web;

import org.springframework.security.oauth2.jwt.Jwt;

import com.springbook.application.sjhm.API_springboot.Model.User.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.CreateUserParameters;

import java.util.UUID;
public record CreateUserRequest(String mobileToken) { 
    
    public CreateUserParameters toParameters(Jwt jwt) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));
        String email = jwt.getClaimAsString("email"); 
        return new CreateUserParameters(authServerId, email, mobileToken);
    }
}
