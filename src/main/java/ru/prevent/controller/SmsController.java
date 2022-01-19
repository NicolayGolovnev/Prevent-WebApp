package ru.prevent.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import ru.prevent.SmsVerification.SmsCode;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Controller
@Slf4j
public class SmsController {

    @GetMapping("/code/sms")
    public String createSmsCode(@RequestParam String mobile, HttpServletResponse response) throws IOException {
        Random rand = new Random();
        String smsCode = Integer.toString(rand.nextInt(100000));
        Cookie cookie = new Cookie(mobile, smsCode);
        cookie.setPath("/");
        cookie.setMaxAge(120);
        response.addCookie(cookie);
        // Output verification code to console instead of SMS sending service
        log.info("Your login verification code is:" + smsCode + "，Valid for 60 seconds");
        return "redirect:/login";
    }
}