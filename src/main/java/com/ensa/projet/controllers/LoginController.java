package com.ensa.projet.controllers;


import com.ensa.projet.controllers.forms.LoginForm;
import com.ensa.projet.metier.UserService;
import com.ensa.projet.security.JwtAuthenticationResponse;
import com.ensa.projet.security.JwtTokenProvider;
import com.ensa.projet.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider tokenProvider;
    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginForm.getUsername(),
                                loginForm.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        Cookie cookie = new Cookie("jwt",jwt);
        response.addCookie(cookie);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setUser((UserPrincipal) authentication.getPrincipal());
        return ResponseEntity.ok(jwtAuthenticationResponse);
    }

}
