package Shoppera.controller;

import Shoppera.entity.Product;
import Shoppera.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product products){
        return ResponseEntity.ok(productService.addProducts(products));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
