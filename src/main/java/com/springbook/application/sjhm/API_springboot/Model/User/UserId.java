package com.springbook.application.sjhm.API_springboot.Model.User;

import com.springbook.application.sjhm.orm.jpa.AbstractEntityId;
import java.util.UUID;
public class UserId extends AbstractEntityId<UUID> {

    protected UserId() { }

    public UserId(UUID id) { 
        super(id);
    }
}