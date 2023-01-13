package com.shubham.nmsbackend.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AddControllerRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String ip;

    @NotBlank
    private String password;

    public AddControllerRequest() {
    }

    public AddControllerRequest(String email, String ip, String password) {
        this.email = email;
        this.ip = ip;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
