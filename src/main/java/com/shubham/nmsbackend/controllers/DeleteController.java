package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.repository.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class DeleteController {
    @Autowired
    ControllerRepository controllerRepository;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteController(@RequestParam("id") String id) {
        try {
            controllerRepository.deleteById(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
