package com.tinoco.userapi.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserDetailService {
    org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Optional<UserDetails> loadUserByJwtToken(String jwtToken);
}
