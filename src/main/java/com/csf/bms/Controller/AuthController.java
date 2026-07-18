package com.csf.bms.Controller;

import com.csf.bms.Dto.UserDto;
import com.csf.bms.Service.JwtUtil;
import com.csf.bms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register — naya user banega
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.registerUser(userDto);
        return ResponseEntity.ok(savedUser);
    }

    // Login — token milega
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        // Email aur password verify karo
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );

        // Token bana ke  aur bhejenge
        String token = jwtUtil.generateToken(userDto.getEmail());
        return ResponseEntity.ok("Bearer " + token);
    }
}