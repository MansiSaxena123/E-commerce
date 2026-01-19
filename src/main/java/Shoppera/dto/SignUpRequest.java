package Shoppera.dto;

import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class SignUpRequest {

    @JoinColumn(unique = true)
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
