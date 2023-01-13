package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.models.DockerController;
import com.shubham.nmsbackend.models.User;
import com.shubham.nmsbackend.payload.request.AddControllerRequest;
import com.shubham.nmsbackend.payload.request.ResetRequest;
import com.shubham.nmsbackend.payload.response.AddControllerResponse;
import com.shubham.nmsbackend.payload.response.MessageResponse;
import com.shubham.nmsbackend.repository.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AddController {
    @Autowired
    ControllerRepository controllerRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/add")
    public ResponseEntity<?> addController(@Valid @RequestBody AddControllerRequest addControllerRequest) {
        if (controllerRepository.existsByIp(addControllerRequest.getIp())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: IP address entered already exists!"));
        }

        //add new controller
        DockerController dc = new DockerController(addControllerRequest.getEmail(), addControllerRequest.getIp(),
                encoder.encode(addControllerRequest.getPassword()));
        controllerRepository.save(dc);

        return ResponseEntity.ok(new AddControllerResponse(dc.getIp()));
    }
}
