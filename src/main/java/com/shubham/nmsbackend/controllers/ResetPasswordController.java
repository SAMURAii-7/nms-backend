package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.models.User;
import com.shubham.nmsbackend.payload.request.ForgotRequest;
import com.shubham.nmsbackend.payload.request.ResetRequest;
import com.shubham.nmsbackend.payload.response.MessageResponse;
import com.shubham.nmsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetRequest resetRequest) {
        if (!userRepository.existsByEmail(resetRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email entered does not exist!"));
        }

        //update password
        User user = userRepository.findByEmail(resetRequest.getEmail()).get();
        user.setPassword(encoder.encode(resetRequest.getPassword()));
        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse("Password Changed!"));
    }
}