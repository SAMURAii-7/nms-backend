package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.payload.response.GetControllersResponse;
import com.shubham.nmsbackend.repository.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class GetControllers {
    @Autowired
    ControllerRepository controllerRepository;

    @GetMapping("/controllers")
    public List<GetControllersResponse> getControllers(@RequestParam(name = "email") String email) {
        return controllerRepository.findAll().stream()
                .filter(c -> c.getEmail().equals(email))
                .map(c -> new GetControllersResponse(c.getId(), c.getIp()))
                .collect(Collectors.toList());
    }
}
