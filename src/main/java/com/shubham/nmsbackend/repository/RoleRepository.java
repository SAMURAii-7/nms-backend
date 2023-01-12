package com.shubham.nmsbackend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shubham.nmsbackend.models.ERole;
import com.shubham.nmsbackend.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}