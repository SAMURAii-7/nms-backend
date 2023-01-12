package com.shubham.nmsbackend.payload.response;

import java.util.List;

public class UserInfoResponse2 {
    private String token;

    public UserInfoResponse2(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
