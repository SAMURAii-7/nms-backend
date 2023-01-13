package com.shubham.nmsbackend.payload.response;

public class GetControllersResponse {
    private String id;
    private String ip;

    public GetControllersResponse(String id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }
}
