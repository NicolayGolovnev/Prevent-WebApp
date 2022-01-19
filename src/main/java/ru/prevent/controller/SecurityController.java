package ru.prevent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String mobile, @RequestParam String smsCode, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(mobile)).findFirst().orElse(null);
        if (!cookie.getValue().equals(smsCode))
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
