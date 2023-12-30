package org.cargocompass.app.mailservice.service;
import com.transmarket.app.commondata.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public interface MailingService {
    void sendForgotPasswordEmail(String email, String userName, String lastName, String link);

    void sendEmail(String emailTemplate, Map<String, Object> params, String subject, Set<String> to);

    void sendUserAccountCreatedEmail(UserDto user);

}