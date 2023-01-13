package com.shubham.nmsbackend.controllers;

import com.shubham.nmsbackend.models.DockerController;
import com.shubham.nmsbackend.payload.request.UpdateControllerRequest;
import com.shubham.nmsbackend.repository.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UpdateController {
    @Autowired
    ControllerRepository controllerRepository;

    @PutMapping("/edit")
    public ResponseEntity<String> updateController(@RequestBody UpdateControllerRequest data) {
        try {
            Optional<DockerController> existingData = controllerRepository.findById(data.getId());
            if(existingData.isPresent()){
                DockerController existingDataObj = existingData.get();
                existingDataObj.setIp(data.getIp());
                existingDataObj.setPassword(data.getPassword());
                controllerRepository.save(existingDataObj);
                return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
