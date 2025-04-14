package com.springbook.application.sjhm.API_springboot.Repository.User;

import java.util.UUID;

import com.springbook.application.sjhm.API_springboot.Model.User.UserId;
import com.springbook.application.sjhm.orm.jpa.UniqueIdGenerator;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public UserRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public UserId nextId() {
        return new UserId(generator.getNextUniqueId());
    }
}