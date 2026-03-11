package Shoppera.controller;


import Shoppera.entity.Cart;
import Shoppera.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addCart")
    public Cart addProductToCart( @RequestParam Long userId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int quantity){

        return cartService.addProductToCart(userId,productId,quantity);
    }

    @GetMapping("/cartTotal")
    public double cartTotal(@RequestParam Long userId){
        return cartService.cartTotal(userId);
    }
}
