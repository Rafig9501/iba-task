package com.ibabanking.zuul.service;

import com.ibabanking.zuul.entity.UserEntity;
import com.ibabanking.zuul.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> fromDb = userRepository.findByUsername(username);
        if (fromDb.isPresent()) {
            List<SimpleGrantedAuthority> authorities = fromDb.get().getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return new User(fromDb.get().getUsername(), fromDb.get().getPassword(), authorities);
        }
        return null;
    }
}
