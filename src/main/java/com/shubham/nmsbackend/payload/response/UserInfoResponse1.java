package com.shubham.nmsbackend.payload.response;

import java.util.List;

public class UserInfoResponse1 {
    private String id;
    private String username;
    private String email;
    private boolean mfa;

    public UserInfoResponse1(String id, String username, String email, boolean mfa) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mfa = mfa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMfa() {
        return mfa;
    }

    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
