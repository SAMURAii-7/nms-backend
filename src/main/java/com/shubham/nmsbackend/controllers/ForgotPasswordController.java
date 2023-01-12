package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.payload.request.ForgotRequest;
import com.shubham.nmsbackend.payload.response.MessageResponse;
import com.shubham.nmsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/forgot-password")
    public ResponseEntity<?>  forgotPassword(@Valid @RequestBody ForgotRequest forgotRequest) {
        if (!userRepository.existsByEmail(forgotRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email entered does not exist!"));
        }

        return ResponseEntity.ok(new MessageResponse("Valid Email"));
    }
}
