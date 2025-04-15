package com.springbook.application.sjhm.API_springboot.Model.User.Web;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbook.application.sjhm.API_springboot.Model.User.AuthServerId;
import com.springbook.application.sjhm.API_springboot.Model.User.CreateUserParameters;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/me") 
    public Map<String, Object> myself(@AuthenticationPrincipal Jwt jwt) { 
        Optional<User> userByAuthServerId = userService.findUserByAuthServerId(new
        AuthServerId(UUID.fromString(jwt.getSubject())));
        Map<String, Object> result = new HashMap<>();
        userByAuthServerId.ifPresent(user -> result.put("userId", user.getId().
        asString()));
        result.put("subject", jwt.getSubject());
        result.put("claims", jwt.getClaims());
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public UserDTO createUser(@AuthenticationPrincipal Jwt jwt, @RequestBody CreateUserRequest request) { 
        CreateUserParameters parameters = request.toParameters(jwt); 
        User user = userService.createUser(parameters);
        return UserDTO.fromUser(user); 
    }

}