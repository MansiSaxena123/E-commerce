package Shoppera.service;

import Shoppera.dto.*;
import Shoppera.entity.Cart;
import Shoppera.entity.CartItem;
import Shoppera.entity.Product;
import Shoppera.entity.User;
import Shoppera.repository.UserRepository;
import Shoppera.utils.AuthUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthService(AuthenticationManager authenticationManager, AuthUtil authUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.authUtil = authUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


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

        emailService.sendMail(user.getEmail(),user.getUsername());
        return new SignUpResponse(user.getId(),user.getUsername());
    }

    public UserResponseDTO getUserByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = user.getCart();

        List<CartItemDTO> cartItemDTOS = new ArrayList<>();

        for (CartItem item : cart.getCartItem()){
            Product product = item.getProduct();
            ProductDTO productDTO = ProductDTO.builder()
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .build();

            CartItemDTO cartItemDTO = CartItemDTO.builder()
                    .productDTO(productDTO)
                    .quantity(item.getQuantity())
                    .build();

            cartItemDTOS.add(cartItemDTO);
        }

        CartDTO cartDTO = CartDTO.builder()
                .cartItemDTOS(cartItemDTOS).build();

        return UserResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .cartDTO(cartDTO).build();
    }
}
