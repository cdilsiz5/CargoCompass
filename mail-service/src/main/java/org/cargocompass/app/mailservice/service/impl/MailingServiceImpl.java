package org.cargocompass.app.mailservice.service.impl;

import com.transmarket.app.commondata.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.cargocompass.app.mailservice.service.MailingService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.cargocompass.app.mailservice.config.EmailConfiguration;
import org.cargocompass.app.mailservice.model.Email;
import org.cargocompass.app.mailservice.service.EmailSenderService;

import java.util.Map;
import java.util.Set;

import static org.cargocompass.app.mailservice.util.AppSettingsKey.APPLICATION_SOURCE_URL_TEMPLATE_KEY;

@RequiredArgsConstructor
@Service
public class MailingServiceImpl implements MailingService {
    private static final String TEMPLATE_FORGOT_PASSWORD = "forgotPassword";
    private static final String TEMPLATE_NEW_USER_CREATED = "newUserCreated";
    private static final String SEND_EMAIL_FIRST_NAME = "firstName";
    private static final String SEND_EMAIL_LAST_NAME = "lastName";

    private final EmailSenderService emailSender;
    @Override
    public void sendForgotPasswordEmail(String to, String firstName, String lastName, String link) {
        final Email email = Email.EmailBuilder.anEmail()
                .withFrom(getFromMailAddress())
                .withTo(to)
                .withSubject(getForgotPasswordEmailSubject())
                .withTemplate(TEMPLATE_FORGOT_PASSWORD)
                .withParameter("firstName", firstName)
                .withParameter("lastName", lastName)
                .withParameter("url", link)
                .withParameter(APPLICATION_SOURCE_URL_TEMPLATE_KEY, getApplicationBaseUrl())
                .build();
        sendEmail(email);
    }
    @Override
    @Async(value = EmailConfiguration.MAIL_SEND_EXECUTOR)
    public void sendEmail(String emailTemplate, Map<String, Object> params, String subject, Set<String> to) {
        final Email email = Email.EmailBuilder.anEmail()
                .withFrom(getFromMailAddress())
                .withTo(to)
                .withSubject(subject)
                .withTemplate(emailTemplate)
                .withParameters(params)
                .build();
        sendEmail(email);
    }



    @Override
    public void sendUserAccountCreatedEmail(UserDto user) {
        final Email email = Email.EmailBuilder.anEmail()
                .withFrom(getFromMailAddress())
                .withTo(user.getUserEmail())
                .withSubject(getCreatePasswordEmailSubject())
                .withTemplate(TEMPLATE_NEW_USER_CREATED)
                .withParameter(SEND_EMAIL_FIRST_NAME, user.getUserFirstName())
                .withParameter(SEND_EMAIL_LAST_NAME, user.getUserLastName())
                .withParameter(APPLICATION_SOURCE_URL_TEMPLATE_KEY, getApplicationBaseUrl())
                .build();
        sendEmail(email);
    }

    @SneakyThrows
    private void sendEmail(Email email) {
        emailSender.sendEmail(email);
    }
    private String getFromMailAddress() {
        return  "cihandilsizdev@gmail.com";
    }

    private String getForgotPasswordEmailSubject() {
        return "Sifrenizi mi unuttunuz";
    }

    private String getCreatePasswordEmailSubject() {
        return "Yeni sifre olusturuldu";
    }
    private String getApplicationBaseUrl() {
        return "http://localhost:3000";
    }


}
