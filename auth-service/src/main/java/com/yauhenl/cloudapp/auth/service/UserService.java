package com.yauhenl.cloudapp.auth.service;

import com.yauhenl.cloudapp.auth.domain.User;
import com.yauhenl.cloudapp.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    public void create(User user) {
        Optional<User> existing = repository.findById(user.getUsername());
        existing.ifPresent(it -> {
            throw new IllegalArgumentException("user already exists: " + it.getUsername());
        });

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("new user has been created: {}", user.getUsername());
    }
}
