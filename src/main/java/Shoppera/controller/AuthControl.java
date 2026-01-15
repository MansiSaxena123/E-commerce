package Shoppera.controller;


import Shoppera.dto.LoginRequest;
import Shoppera.dto.LoginResponse;
import Shoppera.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class AuthControl {

    @Autowired
    AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
//       return ResponseEntity.ok(authService.login(loginRequest));
//    }
}
