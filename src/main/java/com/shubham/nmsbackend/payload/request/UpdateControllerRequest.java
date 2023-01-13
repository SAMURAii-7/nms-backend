package com.shubham.nmsbackend.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UpdateControllerRequest {
    @NotBlank
    private String id;

    @NotBlank
    private String ip;

    @NotBlank
    private String password;

    public UpdateControllerRequest() {
    }

    public UpdateControllerRequest(String ip, String password) {
        this.ip = ip;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
