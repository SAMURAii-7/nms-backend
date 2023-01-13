package com.shubham.nmsbackend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shubham.nmsbackend.models.DockerController;

public interface ControllerRepository extends MongoRepository<DockerController, String> {
    Boolean existsByIp(String ip);
}
