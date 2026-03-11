package Shoppera.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    private List<CartItemDTO> cartItemDTOS;
}
