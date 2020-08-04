package com.ibabanking.login.service;

import com.ibabanking.login.dto.UserDto;
import com.ibabanking.login.entity.UserEntity;
import com.ibabanking.login.jwt.JwtCreator;
import com.ibabanking.login.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtCreator jwtCreator;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, JwtCreator jwtCreator, @Lazy PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtCreator = jwtCreator;
        this.encoder = encoder;
    }

    public ResponseEntity<String> login(UserDto userDto) {
        Optional<UserEntity> userFromDB = userRepository.findByUsername(userDto.getUsername());
        if (userFromDB.isPresent() && encoder.matches(userDto.getPassword(), userFromDB.get().getPassword())) {
            return new ResponseEntity<>(jwtCreator.generateToken(userDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity<UserEntity> register(UserDto userDto) {
        Optional<UserEntity> userFromDB = userRepository.findByUsername(userDto.getUsername());
        if (userFromDB.isPresent())
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        else {
            List<String> authorities = new ArrayList<>();
            authorities.add("EMPLOYEE");
            UserEntity user = new UserEntity(userDto.getUsername(), encoder.encode(userDto.getPassword()), authorities);
            UserEntity save = userRepository.save(user);
            return new ResponseEntity<>(save, HttpStatus.OK);
        }
    }
}
