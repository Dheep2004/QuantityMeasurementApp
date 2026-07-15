package org.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {

    @GetMapping("/api/auth/login")
    public ResponseEntity<Void> login() {

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                java.net.URI.create("/oauth2/authorization/google")
        );

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/api/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                java.net.URI.create("/index.html")
        );

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/api/auth/callback")
    public String callback() {

        return "Authentication Successful";

    }

}