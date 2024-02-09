package com.pro.ukart.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pro.ukart.Dtos.UserLoginDto;
import com.pro.ukart.Entities.User;
import com.pro.ukart.Entities.UserRole;
import com.pro.ukart.Repositories.UserRepository;
import com.pro.ukart.Repositories.UserRoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
     @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User register(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        UserRole userRole = userRoleRepository.findByAuthority("USER").get();
        Set<UserRole> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new User(email, encodedPassword, authorities));
    }

    public UserLoginDto login(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = tokenService.generateJwt(auth);

            User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                return new UserLoginDto(user.getId(), user, token);
            } else {
                return new UserLoginDto(null, null, "");
            }

        } catch (AuthenticationException e) {
            return new UserLoginDto(null, null, "");
        }
    }


    
}
