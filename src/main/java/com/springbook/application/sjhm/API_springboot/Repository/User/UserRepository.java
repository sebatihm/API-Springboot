package com.springbook.application.sjhm.API_springboot.Repository.User;

import org.springframework.data.repository.CrudRepository;

import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;

//tag::class[]
public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom { }