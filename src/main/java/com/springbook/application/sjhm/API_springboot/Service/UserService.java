package com.springbook.application.sjhm.API_springboot.Service;

import com.springbook.application.sjhm.API_springboot.Model.User.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.CreateUserParameters;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserId;
import com.springbook.application.sjhm.API_springboot.Repository.User.UserRepository;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository; 

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public Optional<User> findUserByAuthServerId(AuthServerId authServerId) { 
        return repository.findByAuthServerId(authServerId);
    } 

    public User createUser(CreateUserParameters createUserParameters) {
        UserId userId = repository.nextId();
        User user = new User(userId, createUserParameters.email(),
        createUserParameters.authServerId(),
        createUserParameters.mobileToken());
        return repository.save(user);
    }
}
