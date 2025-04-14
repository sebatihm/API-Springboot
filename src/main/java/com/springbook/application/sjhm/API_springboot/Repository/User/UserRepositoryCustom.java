package com.springbook.application.sjhm.API_springboot.Repository.User;

import com.springbook.application.sjhm.API_springboot.Model.User.UserId;

public interface UserRepositoryCustom {
    UserId nextId();
}