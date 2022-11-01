package com.tinoco.userapi.security;

import com.tinoco.userapi.domain.models.RoleEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Jwts.parser;
import static io.jsonwebtoken.Jwts.parserBuilder;

@Component
public class Jwt {
    private final String ROLES_KEY = "roles";

    private String secretKey;
    private long validityInMilliseconds;

    @Autowired
    public Jwt(@Value("${spring.security.jwt.token.secret}") String secretKey,
               @Value("${spring.security.jwt.token.expirationTime}") long validityInMilliseconds) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(String username, List<RoleEntity> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLES_KEY, roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList()));

        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            getJwtParserBuilder().build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return getJwtParserBuilder().build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public List<GrantedAuthority> getRoles(String token) {
        List<Map<String, String>> roleClaims = getJwtParserBuilder().build()
                .parseClaimsJws(token).getBody().get(ROLES_KEY, List.class);
        return roleClaims.stream().map(roleClaim ->
                        new SimpleGrantedAuthority(roleClaim.get("authority")))
                .collect(Collectors.toList());
    }

    private JwtParserBuilder getJwtParserBuilder() {
        return parserBuilder().setSigningKey(getKey());
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}