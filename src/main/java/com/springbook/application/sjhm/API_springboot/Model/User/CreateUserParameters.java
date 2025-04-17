package com.springbook.application.sjhm.API_springboot.Model.User;

import com.springbook.application.sjhm.API_springboot.Model.Converter.AuthServerId;

public record CreateUserParameters(AuthServerId authServerId, String email, String
mobileToken) {
}