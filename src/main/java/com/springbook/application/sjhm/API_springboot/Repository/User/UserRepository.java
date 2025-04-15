package com.springbook.application.sjhm.API_springboot.Repository.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springbook.application.sjhm.API_springboot.Model.User.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;

public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom {
    Optional<User> findByAuthServerId(AuthServerId authServerId);
}