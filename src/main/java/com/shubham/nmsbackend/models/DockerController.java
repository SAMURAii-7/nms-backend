package com.shubham.nmsbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "controllers")
public class DockerController {
    @Id
    private String id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 15)
    private String ip;

    @NotBlank
    @Size(max = 120)
    private String password;

    public DockerController() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DockerController(String email, String ip, String password) {
        this.email = email;
        this.ip = ip;
        this.password = password;
    }
    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    };

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
