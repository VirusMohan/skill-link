package com.app.java.skill_link.src.Controller;

import com.app.java.skill_link.src.entity.Creds;
import com.app.java.skill_link.src.jwt.JwtUtils;
import com.app.java.skill_link.src.service.CredsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/admin")
public class CredsController {

    private final CredsService credsService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public CredsController(CredsService credsService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.credsService = credsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/register-user")
    public ResponseEntity<String> registerUser(@RequestBody Creds creds){
        return new ResponseEntity<>(credsService.updateCredentials(creds.getUsername(),creds.getPassword()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/update-user")
    public ResponseEntity<String> updateUser(@RequestBody Creds creds){
        return new ResponseEntity<>(credsService.updateCredentials(creds.getUsername(),creds.getPassword()),
            HttpStatus.OK);
    }

    @GetMapping(value = "/validate-user")
    public ResponseEntity<String> validateUser(@RequestBody Creds creds){
        return new ResponseEntity<>(credsService.validateUser(creds.getUsername(),creds.getPassword())?
                "User Validated" : "Invalid user credentials",
                HttpStatus.OK);
    }

    @PostMapping(value = "/token")
    public ResponseEntity<String> tokenGenerator(@RequestBody Creds creds){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateToken(creds.getUsername());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
