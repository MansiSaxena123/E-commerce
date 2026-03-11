package Shoppera.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private String username;
    private String email;
    private String phoneNumber;
    private CartDTO cartDTO;
}
