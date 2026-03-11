package Shoppera.controller;


import Shoppera.dto.*;
import Shoppera.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthControl {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
       return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDTO> getUserByUserId(@RequestParam Long userId){
        return ResponseEntity.ok((authService.getUserByUserId(userId)));
    }
}
