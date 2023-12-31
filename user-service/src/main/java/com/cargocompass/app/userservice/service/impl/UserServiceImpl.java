package com.cargocompass.app.userservice.service.impl;

import com.cargocompass.app.userservice.constants.UserConstants;
import com.cargocompass.app.userservice.dto.UserDto;
import com.cargocompass.app.userservice.exception.UserAlreadyExistException;
import com.cargocompass.app.userservice.exception.UserNotFoundException;
import com.cargocompass.app.userservice.mapper.UserMapper;
import com.cargocompass.app.userservice.model.PasswordResetToken;
import com.cargocompass.app.userservice.model.Role;
import com.cargocompass.app.userservice.model.User;
import com.cargocompass.app.userservice.model.enums.UserRole;
import com.cargocompass.app.userservice.repository.PasswordResetTokenRepository;
import com.cargocompass.app.userservice.repository.RoleRepository;
import com.cargocompass.app.userservice.repository.UserRepository;
import com.cargocompass.app.userservice.request.ChangePasswordRequest;
import com.cargocompass.app.userservice.request.CreateUserRequest;
import com.cargocompass.app.userservice.security.UserDetailsImpl;
import com.cargocompass.app.userservice.service.UserService;
import com.cargocompass.app.userservice.util.AppSettingsKey;
import com.cargocompass.app.userservice.request.UpdatUserRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    @Transactional
    public UserDto updateUser(UpdatUserRequest request, Long userId) {
        User user= findUserById(userId);
        user=updateUserFromRequest(user,request);
        User updatedUser=userRepository.save(user);
        return UserMapper.USER_MAPPER.toUserDto(updatedUser);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveUser(CreateUserRequest request) {
        if (existByEmail(request.getUserEmail())) {
            throw new UserAlreadyExistException("User already exist");
        }
        User user = UserMapper.USER_MAPPER.createUser(request);
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        Role userRole = roleRepository.findByUserRoleEquals(UserRole.ROLE_ADMIN).orElseThrow(()->new RuntimeException("Role Not Found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        return ResponseEntity.ok(UserMapper.USER_MAPPER.toUserDto(userRepository.save(user)));
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest, String email) {
        User user = findUserByEmail(email);
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getUserPassword())) {
            throw new RuntimeException(UserConstants.CURRENT_AND_BEFORE_PASSWORD_NOT_MATCH);
        }
        String encodedNewPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setUserPassword(encodedNewPassword);
        userRepository.save(user);
        //todo:mailingClient.sendYourPasswordChanged()
    }

    @Override
    public void sendForgotPasswordMail(String email) {
        User user = findUserByEmail(email);
        createPasswordResetTokenForUser(email);
        final String link = createForgotPasswordLink(user);
        // todo: mailingClient.sendForgotPasswordEmail(email,token,link);
    }



     @Transactional
     public boolean resetUserPassword(String token, String newPassword) {
         PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);

         if (resetToken == null || resetToken.isExpired()) {
             return false;
         }
         User user = resetToken.getUser();
         if (user == null) {
             return false;
         }
         String encodedPassword = passwordEncoder.encode(newPassword);
         user.setUserPassword(encodedPassword);
         userRepository.save(user);
         passwordResetTokenRepository.delete(resetToken);
         return true;
     }

    @Override
    public UserDto getUserById(Long id) {
        return UserMapper.USER_MAPPER.toUserDto(findUserById(id));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserDetailsImpl.build(userRepository.findByUserEmail(email).orElseThrow(()->new UserNotFoundException(UserConstants.USER_NOT_FOUND)));
    }
    public boolean existByEmail(String email) {
        return userRepository.existsByUserEmail(email);
    }


    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
    }

    private User findUserByEmail(String email) {
        return userRepository.findByUserEmail(email).orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
    }
    private String createPasswordResetTokenForUser(String email) {
        User user =  findUserByEmail(email);
        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setUser(user);
        myToken.setExpiryDate(calculateExpiryDate(24 * 60));
        passwordResetTokenRepository.save(myToken);
        return token;
    }
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
    private String createForgotPasswordLink(User user) {
        final String token =createPasswordResetTokenForUser(user.getUserEmail());
        String url = null;
        if (StringUtils.isEmpty(url)) {
            url = "http://localhost:3000";
        }
        return String.format(AppSettingsKey.RESET_PASSWORD_URL_FORMAT, url,token);
    }
    public static User updateUserFromRequest(User entity, UpdatUserRequest request ) {
        if (request.getUserEmail() != null && !request.getUserEmail().isEmpty()) {
            entity.setUserEmail(request.getUserEmail());
        }
        if (request.getUserPhoneNumber() != null && !request.getUserPhoneNumber().isEmpty()) {
            entity.setUserPhoneNumber(request.getUserPhoneNumber());
        }
        if (request.getUserPassword() != null && !request.getUserPassword().isEmpty()) {
            entity.setUserPassword(request.getUserPassword());
        }
        return entity;
    }
}
