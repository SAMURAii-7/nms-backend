package com.shubham.nmsbackend.payload.response;

public class AddControllerResponse {
    private String ip;

    public AddControllerResponse(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
