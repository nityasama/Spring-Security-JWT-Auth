package com.nitya.spring_security.service;

import com.nitya.spring_security.dto.SignUpRequest;
import com.nitya.spring_security.entities.User;
import com.nitya.spring_security.exceptions.DuplicateException;
import com.nitya.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public void signup(SignUpRequest request) {
        String email = request.email();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        User user = new User(null, request.name(), email, hashedPassword);
        userRepository.add(user);
    }

}
