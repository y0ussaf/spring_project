package com.ensa.projet.security;

public class JwtAuthenticationResponse {


    public UserPrincipal getUser() {
        return user;
    }

    public void setUser(UserPrincipal user) {
        this.user = user;
    }

    private UserPrincipal user;




}