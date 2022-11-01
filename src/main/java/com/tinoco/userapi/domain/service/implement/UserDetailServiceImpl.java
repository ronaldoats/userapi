package com.tinoco.userapi.domain.service.implement;

import com.tinoco.userapi.domain.models.UserEntity;
import com.tinoco.userapi.domain.repository.UserRepository;
import com.tinoco.userapi.domain.service.UserDetailService;
import com.tinoco.userapi.error.EmailException;
import com.tinoco.userapi.security.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    private UserRepository userRepository;
    private Jwt jwt;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository,
                                 Jwt jwt
    ) {
        this.userRepository = userRepository;
        this.jwt = jwt;
    }

    @Override
    public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
        if (this.jwt.isValidToken(jwtToken)) {
            return Optional.of(
                    withUsername(this.jwt.getUsername(jwtToken))
                            .authorities(this.jwt.getRoles(jwtToken))
                            .password("")
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }
        return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new EmailException(String.format("Usuario %s no existe", username)));

        return withUsername(user.getEmail())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
