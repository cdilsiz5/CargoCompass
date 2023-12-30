package com.transmarket.app.userservice.feign;

import com.transmarket.app.userservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mailing-service")
public interface MailingClient {

    @PostMapping("/send-email")
    void sendEmail(@RequestBody UserDto details);
}