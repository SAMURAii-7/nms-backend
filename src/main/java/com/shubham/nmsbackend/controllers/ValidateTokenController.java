package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.payload.request.ValidateTokenRequest;
import com.shubham.nmsbackend.payload.response.MessageResponse;
import com.shubham.nmsbackend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ValidateTokenController {
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/validate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody ValidateTokenRequest validateRequest) {

        if (jwtUtils.validateJwtToken(validateRequest.getToken())) {
            return ResponseEntity.ok(new MessageResponse("Valid Token"));
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
