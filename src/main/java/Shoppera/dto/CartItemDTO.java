package Shoppera.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {

    private ProductDTO productDTO;
    private int quantity;
}
