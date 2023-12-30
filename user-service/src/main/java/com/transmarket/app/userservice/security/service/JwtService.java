package com.transmarket.app.userservice.security.service;

import com.transmarket.app.userservice.exception.UserNotFoundException;
import com.transmarket.app.userservice.model.User;
import com.transmarket.app.userservice.repository.UserRepository;
import com.transmarket.app.userservice.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.transmarket.app.userservice.model.Role;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    private final UserRepository userRepository;

    public String generateJwtToken(UserDetailsImpl userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }




    public String generateTokenFromUsername(String username) {
        User user = userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
         String roles = user.getRoles().stream()
                .map(Role::getUserRole)
                .map(Enum::name)
                .collect(Collectors.joining(","));
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", username);
        claims.put("roles", roles);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    }
