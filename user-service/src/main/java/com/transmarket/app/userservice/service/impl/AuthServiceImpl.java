package com.transmarket.app.userservice.service.impl;

import com.transmarket.app.userservice.exception.TokenRefreshException;
import com.transmarket.app.userservice.exception.UserNotFoundException;
import com.transmarket.app.userservice.model.RefreshToken;
import com.transmarket.app.userservice.model.Role;
import com.transmarket.app.userservice.model.User;
import com.transmarket.app.userservice.model.enums.UserRole;
import com.transmarket.app.userservice.repository.RoleRepository;
import com.transmarket.app.userservice.repository.UserRepository;
import com.transmarket.app.userservice.request.CreateUserRequest;
import com.transmarket.app.userservice.request.LoginRequest;
import com.transmarket.app.userservice.request.TokenRefreshRequest;
import com.transmarket.app.userservice.response.JwtResponse;
import com.transmarket.app.userservice.response.TokenRefreshResponse;
import com.transmarket.app.userservice.security.UserDetailsImpl;
import com.transmarket.app.userservice.security.service.JwtService;
import com.transmarket.app.userservice.security.service.RefreshTokenService;
import com.transmarket.app.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

import static com.transmarket.app.userservice.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;



    public ResponseEntity<?> Login(LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtService.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        User user = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow(()->new UserNotFoundException("User Not found"));
        return ResponseEntity.ok(new JwtResponse(jwt,refreshToken.getToken(),USER_MAPPER.toUserDto(user)));
    }
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("Log out successful!");
    }
    public ResponseEntity<?> refreshtoken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateTokenFromUsername(user.getUserEmail());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}

