package Shoppera.service;

import Shoppera.entity.Product;
import Shoppera.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProducts(Product products) {

        Product product1 = productRepository.findByProductName(products.getProductName()).orElse(null);

        if(product1 != null) throw new IllegalArgumentException("Product already exists..");

        product1 = productRepository.save(Product.builder()
                .productName(products.getProductName())
                .price(products.getPrice())
                .stock(products.getStock())
                .build());

        return product1;
    }

    public List<Product> getAllProducts(){
       return productRepository.findAll();
    }
}
