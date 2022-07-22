package fr.shewbii.sojahbbackend.security.model;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;

    public TokenResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
