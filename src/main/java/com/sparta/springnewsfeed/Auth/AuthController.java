package com.sparta.springnewsfeed.Auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
    public class AuthController {

        public static final String AUTHORIZATION_HEADER = "Authorization";

        @GetMapping("/create-cookie")
        public String createCookie(HttpServletResponse res) {
            addCookie("Robbie Auth", res);

            return "createCookie";
        }

        @GetMapping("/get-cookie")
        public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
            System.out.println("value = " + value);

            return "getCookie : " + value;
        }

        public static void addCookie(String cookieValue, HttpServletResponse res) {
            cookieValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8).replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        }
    }
