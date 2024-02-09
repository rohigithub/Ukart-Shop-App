package com.pro.ukart.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pro.ukart.Dtos.CartDto;
import com.pro.ukart.Exceptions.AppException;
import com.pro.ukart.Service.CartService;
import com.pro.ukart.Service.ServiceLayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController{

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getCartByUserId(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        CartDto cartDto = cartService.getCartByUserId(userId);
        if (cartDto != null) {
            response.put("cart", cartDto);
            response.put("numberOfItemsInCart", cartService.getNumberOfItemsInCart(userId));
            return ResponseEntity.ok().body(response);
        } else {
            throw new AppException("User's cart not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/{productId}/{quantity}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable int quantity) {
        CartDto cartDto = cartService.addItemToCart(userId, productId, quantity);
        return ResponseEntity.ok().body(cartDto);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        CartDto cartDto = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok().body(cartDto);
    }

//     @Autowired
//     private ServiceLayer serviceLayer;

//     @PostMapping("/cart")
//     public ResponseEntity<String> findData(@RequestBody Map<String, String> request) {
//         //check if uname psw matches
//         String email = request.get("email");
//         String password = request.get("password");
//         String response = serviceLayer.EmailPasswordMatch(email, password);
//         if (Objects.equals(response, "Incorrect email or password provided")){
//             return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//         }
//         // , cart exists, and cart not empty
//         response = String.valueOf(serviceLayer.getCart(email,password));
//         return new ResponseEntity<>(response, HttpStatus.OK);
// //        return new ResponseEntity<>("cart", HttpStatus.OK);
//     }

//     @PostMapping("/cart/add")
//     public ResponseEntity<String> addToCart(@RequestBody Map<String, String> request) {
//         //check if uname psw matches
//         String email = request.get("email");
//         String password = request.get("password");

//         String response = serviceLayer.EmailPasswordMatch(email, password);
//         if (Objects.equals(response, "Incorrect email or password provided")){
//             return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//         }

//         long product_id = Long.parseLong(request.get("product_id"));
//         // add product to cart of current user
//         response = String.valueOf(serviceLayer.addProductToCart(email,password,product_id));
//         return new ResponseEntity<>(response, HttpStatus.OK);
//     }

//     @PostMapping("/cart/update")
//     public ResponseEntity<String> updateItemQuantity(@RequestBody Map<String, String> request) {
//         //check if uname psw matches
//         String email = request.get("email");
//         String password = request.get("password");
//         int quantity = Integer.parseInt(request.get("quantity"));

//         String response = serviceLayer.EmailPasswordMatch(email, password);
//         if (Objects.equals(response, "Incorrect email or password provided")){
//             return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//         }

//         long product_id = Long.parseLong(request.get("product_id"));
//         // add product to cart of current user
//         response = String.valueOf(serviceLayer.updateProductQuantity(email,password,product_id,quantity));
//         return new ResponseEntity<>(response, HttpStatus.OK);
//     }

//     @PostMapping("/cart/remove")
//     public ResponseEntity<String> removeProduct(@RequestBody Map<String, String> request) {
//         //check if uname psw matches
//         String email = request.get("email");
//         String password = request.get("password");

//         String response = serviceLayer.EmailPasswordMatch(email, password);
//         if (Objects.equals(response, "Incorrect email or password provided")){
//             return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//         }

//         long product_id = Long.parseLong(request.get("product_id"));
//         // add product to cart of current user
//         response = String.valueOf(serviceLayer.updateProductQuantity(email,password,product_id,0));
//         return new ResponseEntity<>(response, HttpStatus.OK);
//     }

//     @PostMapping("/cart/clear")
//     public ResponseEntity<String> clearCart(@RequestBody Map<String, String> request) {
//         //check if uname psw matches
//         String email = request.get("email");
//         String password = request.get("password");

//         String response = serviceLayer.EmailPasswordMatch(email, password);
//         if (Objects.equals(response, "Incorrect email or password provided")){
//             return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//         }

//         response = String.valueOf(serviceLayer.clearCart(email,password));
//         return new ResponseEntity<>(response, HttpStatus.OK);
//     }

 }