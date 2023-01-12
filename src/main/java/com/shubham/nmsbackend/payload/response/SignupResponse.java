package com.shubham.nmsbackend.payload.response;

public class SignupResponse {
    private boolean mfa;
    private String secretImageUri;


    public SignupResponse(boolean mfa, String secretImageUri) {
        this.mfa = mfa;
        this.secretImageUri = secretImageUri;
    }

    public boolean isMfa() {
        return mfa;
    }

    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }

    public String getSecretImageUri() {
        return secretImageUri;
    }

    public void setSecretImageUri(String secretImageUri) {
        this.secretImageUri = secretImageUri;
    }
}
