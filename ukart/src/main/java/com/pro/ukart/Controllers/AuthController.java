package com.pro.ukart.Controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pro.ukart.Dtos.UserDto;
import com.pro.ukart.Dtos.UserLoginDto;
import com.pro.ukart.Entities.User;
import com.pro.ukart.Exceptions.AppException;
import com.pro.ukart.Service.*;

import jakarta.validation.Valid;

import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserDto user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new AppException("All fields are required.", HttpStatus.BAD_REQUEST);
        }

        return authenticationService.register(user.getEmail(), user.getPassword());
    }

    @PostMapping("/login")
    public UserLoginDto login(@Valid @RequestBody UserDto user) {
        UserLoginDto userLoginDto = authenticationService.login(user.getEmail(), user.getPassword());

        if (userLoginDto.getUser() == null) {
            throw new AppException("Invalid credentials.", HttpStatus.NOT_FOUND);
        }

        return userLoginDto;
    }

    /*@Autowired
    private ServiceLayer serviceLayer;

    @PostMapping("/login")
    public ResponseEntity<String> findData(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        String response = serviceLayer.EmailPasswordMatch(email, password);
        HttpStatus httpStatus = HttpStatus.OK;

        if (Objects.equals(response, "Incorrect email or password provided")){
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");

        String response = serviceLayer.registerUser(name, email, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> getUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        String response = serviceLayer.EmailPasswordMatch(email, password);
        if (Objects.equals(response, "Incorrect email or password provided")){
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        response = String.valueOf(serviceLayer.getUser(email,password));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/





    
}
