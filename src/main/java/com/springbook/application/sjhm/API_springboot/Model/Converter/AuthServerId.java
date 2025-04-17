package com.springbook.application.sjhm.API_springboot.Model.Converter;

import org.springframework.util.Assert;
import java.util.UUID;

public record AuthServerId(UUID value) {

    public AuthServerId {
        Assert.notNull(value, "The AuthServerId value should not be null");
    }
    
}