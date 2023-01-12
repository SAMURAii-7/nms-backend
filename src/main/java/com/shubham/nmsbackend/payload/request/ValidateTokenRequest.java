package com.shubham.nmsbackend.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ValidateTokenRequest {
    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
