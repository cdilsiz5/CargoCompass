package com.cargocompass.app.userservice.service.impl;

import com.cargocompass.app.userservice.exception.TokenRefreshException;
import com.cargocompass.app.userservice.exception.UserNotFoundException;
import com.cargocompass.app.userservice.mapper.UserMapper;
import com.cargocompass.app.userservice.model.RefreshToken;
import com.cargocompass.app.userservice.model.User;
import com.cargocompass.app.userservice.repository.UserRepository;
import com.cargocompass.app.userservice.request.LoginRequest;
import com.cargocompass.app.userservice.response.TokenRefreshResponse;
import com.cargocompass.app.userservice.security.UserDetailsImpl;
import com.cargocompass.app.userservice.security.service.JwtService;
import com.cargocompass.app.userservice.security.service.RefreshTokenService;
import com.cargocompass.app.userservice.request.TokenRefreshRequest;
import com.cargocompass.app.userservice.response.JwtResponse;
import com.cargocompass.app.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        return ResponseEntity.ok(new JwtResponse(jwt,refreshToken.getToken(), UserMapper.USER_MAPPER.toUserDto(user)));
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

