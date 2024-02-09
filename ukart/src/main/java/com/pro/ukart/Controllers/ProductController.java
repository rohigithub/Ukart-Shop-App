package com.pro.ukart.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pro.ukart.Dtos.ProductDto;
import com.pro.ukart.Entities.Product;
import com.pro.ukart.Exceptions.AppException;
//import com.pro.ukart.Service.ServiceLayer;
import com.pro.ukart.Service.*;
import java.math.BigDecimal;
import java.util.List;
//import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping("add")
    public Product add(@RequestBody ProductDto productDto) {
        if (
                productDto.getName() == null ||
                productDto.getName().isEmpty() ||
                productDto.getDescription() == null || productDto.getDescription().isEmpty() ||
                productDto.getImage() == null || productDto.getImage().isEmpty() ||
                productDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {

            throw new AppException("All fields are required.", HttpStatus.BAD_REQUEST);
        }
        Product newProduct = new Product();
        newProduct.setName(productDto.getName());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setImage(productDto.getImage());

        return productService.add(newProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        Optional<Product> productOptional = productService.getProductById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            throw new AppException("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    /*@Autowired
    private ServiceLayer serviceLayer;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = serviceLayer.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("id") Long id) {
        Optional<Product> product = serviceLayer.getProduct(id);
        if (product.isPresent()){
            return new ResponseEntity<>(product, HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product addedProduct =serviceLayer.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws Exception {
        Optional<Product> existingProduct = serviceLayer.getProduct(id);

        if (existingProduct.isPresent()) {
            Product updatedProduct = serviceLayer.updateProduct(id, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        boolean isDeleted = serviceLayer.deleteProduct(productId);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

}
