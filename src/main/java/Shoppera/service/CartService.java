package Shoppera.service;

import Shoppera.entity.Cart;
import Shoppera.entity.CartItem;
import Shoppera.entity.Product;
import Shoppera.entity.User;
import Shoppera.repository.CartItemRepository;
import Shoppera.repository.CartRepository;
import Shoppera.repository.ProductRepository;
import Shoppera.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartService {


    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart addProductToCart(Long userId, Long productId, int quantity){

        User user = userRepository.findById(userId).orElseThrow();

        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();

            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(productId).orElseThrow();

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductProductId(cart.getId(), productId);
        if (existingItem.isPresent()){

            CartItem item = existingItem.get();

            item.setQuantity(item.getQuantity() + quantity);

            cartItemRepository.save(item);
        }
        else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);

            cartItemRepository.save(item);
            cart.getCartItem().add(item);
        }

        return cart;
    }


    public double cartTotal(Long userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = user.getCart();
        double total = 0;

        for (CartItem item : cart.getCartItem()){

            double price = Double.parseDouble(item.getProduct().getPrice());

            total += price * item.getQuantity();
        }

        return total;
    }
}
