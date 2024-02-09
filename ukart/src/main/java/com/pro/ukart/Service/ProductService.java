package com.pro.ukart.Service;
import java.util.List;
import java.util.Optional;

import com.pro.ukart.Entities.Product;
import com.pro.ukart.Repositories.*;
import com.stripe.param.PaymentLinkUpdateParams.CustomField.Dropdown.Option;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product add(Product product) {
        return productRepository.save(product);

    }
}
