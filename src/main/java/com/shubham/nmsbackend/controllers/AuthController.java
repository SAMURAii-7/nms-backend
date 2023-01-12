package com.shubham.nmsbackend.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.shubham.nmsbackend.payload.request.VerifyCodeRequest;
import com.shubham.nmsbackend.payload.response.SignupResponse;
import com.shubham.nmsbackend.payload.response.UserInfoResponse2;
import com.shubham.nmsbackend.security.services.TotpManager;
import com.shubham.nmsbackend.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.nmsbackend.models.ERole;
import com.shubham.nmsbackend.models.Role;
import com.shubham.nmsbackend.models.User;
import com.shubham.nmsbackend.payload.request.LoginRequest;
import com.shubham.nmsbackend.payload.request.SignupRequest;
import com.shubham.nmsbackend.payload.response.UserInfoResponse1;
import com.shubham.nmsbackend.payload.response.MessageResponse;
import com.shubham.nmsbackend.repository.RoleRepository;
import com.shubham.nmsbackend.repository.UserRepository;
import com.shubham.nmsbackend.security.jwt.JwtUtils;
import com.shubham.nmsbackend.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private TotpManager totpManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if(!userDetails.isMfa()) {
            String jwt = jwtUtils.generateJwt(userDetails.getEmail());
            return ResponseEntity.ok(new UserInfoResponse2(jwt));
        } else {
            return ResponseEntity.ok()
                    .body(new UserInfoResponse1(userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(), userDetails.isMfa()));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@Valid @RequestBody VerifyCodeRequest verifyCodeRequest) {
        String jwt = jwtUtils.generateJwt(verifyCodeRequest.getEmail());
        boolean code = userDetailsService.verify(verifyCodeRequest.getEmail(), verifyCodeRequest.getCode());
        if(!code) {
            throw new RuntimeException("2FA Code not correct");
        }
        return ResponseEntity.ok(new UserInfoResponse2(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }



        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getMfa());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            });
        }

        if(signUpRequest.getMfa()) {
            user.setSecret(totpManager.generateSecret());
            user.setRoles(roles);
            userRepository.save(user);

            return ResponseEntity.ok().body(new SignupResponse(user.getMfa(),
                    totpManager.getUriForImage(user.getSecret(), user.getUsername())));
        } else {
            user.setRoles(roles);
            userRepository.save(user);

            return ResponseEntity.ok().body(new SignupResponse(user.getMfa(), ""));
        }

    }
}