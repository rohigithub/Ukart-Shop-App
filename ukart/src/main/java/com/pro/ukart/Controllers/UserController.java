package com.pro.ukart.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.ukart.Dtos.UserDto;
import com.pro.ukart.Entities.User;
import com.pro.ukart.Service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/user")
public class UserController {
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId, Authentication authentication) {
        return userService.getUserById(userId, authentication);
    }

    @PutMapping("/update/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody UserDto userDto, Authentication authentication) {
        return userService.updateUserById(userId, userDto, authentication);
    }
}
