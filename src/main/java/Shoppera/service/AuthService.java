package Shoppera.service;

import Shoppera.dto.LoginRequest;
import Shoppera.dto.LoginResponse;
import Shoppera.dto.SignUpRequest;
import Shoppera.dto.SignUpResponse;
import Shoppera.entity.User;
import Shoppera.repository.UserRepository;
import Shoppera.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class AuthService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())

        );
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);
        return new LoginResponse(token);

    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        User user = userRepository.findByUsername(signUpRequest.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User already exists..");

        user = userRepository.save(User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .phoneNumber(signUpRequest.getPhoneNumber()).build());

        return new SignUpResponse(user.getId(),user.getUsername());
    }
}
