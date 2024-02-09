package com.pro.ukart.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.ukart.Entities.Cart;
import com.pro.ukart.Entities.CartItem;
import com.pro.ukart.Entities.Product;
import com.pro.ukart.Entities.User;
import com.pro.ukart.Repositories.CartRepository;
import com.pro.ukart.Repositories.ProductRepository;
import com.pro.ukart.Repositories.UserRepository;

@Service
public class ServiceLayer {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

//     public String EmailPasswordMatch(String email, String passowrd) {
//         Optional<User> result = Optional.ofNullable(userRepo.findByEmailAndPassword(email, passowrd));

//         if (result.isPresent()) {
//             return "Email PSW Found";
//         } else {
//             return "Incorrect Email or Password Provided";
//         }
//     }

//     public String registerUser(String name, String email, String password) {
//         User user = new User();
//         user.setName(name);
//         user.setEmail(email);
//         user.setPassword(password);
//         userRepo.save(user);
//         return "Registration successful";

//     }

    
//     public List<Product> getProducts() {
//         return productRepo.findAll();
//     }


//     public Optional<Product> getProduct(long id) {
//         Optional<Product> product =  productRepo.findById(id);
//         return product;
//     }

//     public Product addProduct(Product product) {
//         // Your logic to add a new product to the repository
//         return productRepo.save(product);
//     }

//      // CART related methods start here
//     public String getCart(String email, String password) {
//         User user = userRepo.findByEmailAndPassword(email, password);
//         Cart cart = cartRepo.findByUser(user).orElseGet(() -> createNewCart(user));
//         cart.calculateTotal();

//         if (cart != null) {
//             List<CartItem> cartItems = cart.getCartItems();

//             try {
//                 ObjectMapper objectMapper = new ObjectMapper();
//                 return objectMapper.writeValueAsString(cartItems);
//             } catch (JsonProcessingException e) {
//                 e.printStackTrace(); // Print the exception details for debugging
//                 return "Error converting cart items to JSON: " + e.getMessage();
//             }
//         } else {
//             return "Cart not present"; // Handle the case when the cart is not present
//         }
//     }

//     public String addProductToCart(String email, String password, long productId) {
//         User user = userRepo.findByEmailAndPassword(email, password);
//         Cart cart = cartRepo.findByUser(user).orElseGet(() -> createNewCart(user));

//         Optional<Product> optProduct = productRepo.findById(productId);

//         if (user != null && optProduct.isPresent()) {
//             Product product = optProduct.get();
//             int quantity = 1;

//             CartItem cartItem = new CartItem();
//             cartItem.setProduct(product);
//             cartItem.setQuantity(quantity);

//             cart.addCartItem(cartItem);

//             // Save the changes to the database
//             cartRepo.save(cart);
//             cart.calculateTotal();
//             List<CartItem> cartItems = cart.getCartItems();

//             try {
//                 ObjectMapper objectMapper = new ObjectMapper();
//                 return objectMapper.writeValueAsString(cartItems);
//             } catch (JsonProcessingException e) {
//                 e.printStackTrace(); // Print the exception details for debugging
//                 return "Error converting cart items to JSON: " + e.getMessage();
//             }

// //            return "cart";
//         }

//         else{
//             throw new RuntimeException("Failed to add product to cart.");
//         }
//     }

//     public String updateProductQuantity(String email, String password, long productId, int quantity) {
//         User user = userRepo.findByEmailAndPassword(email, password);
//         Cart cart = cartRepo.findByUser(user).orElseGet(() -> createNewCart(user));

//         Optional<Product> optProduct = productRepo.findById(productId);

//         if (user != null && optProduct.isPresent()) {
//             Product product = optProduct.get();

//             CartItem cartItem = new CartItem();
//             cartItem.setProduct(product);
//             cartItem.setQuantity(quantity);
//             // remove item from cart if quantity reaches 0
//             if (quantity>0){
//                 cart.updateItemQuantity(cartItem,quantity);
//             }
//             else{
//                 cart.removeCartItem(cartItem);
//             }


//             // Save the changes to the database
//             // REPETITIVE CODE
//             cartRepo.save(cart);
//             cart.calculateTotal();
//             List<CartItem> cartItems = cart.getCartItems();

//             try {
//                 ObjectMapper objectMapper = new ObjectMapper();
//                 return objectMapper.writeValueAsString(cartItems);
//             } catch (JsonProcessingException e) {
//                 e.printStackTrace(); // Print the exception details for debugging
//                 return "Error converting cart items to JSON: " + e.getMessage();
//             }
//         }

//         else{
//             throw new RuntimeException("Failed to add product to cart.");
//         }
//     }

//     private Cart createNewCart(User user) {
//         Cart newCart = new Cart();
//         newCart.setUser(user);
//         newCart.setCartItems(new ArrayList<>()); // initialize the cart items list
//         return newCart;
//     }
//     public String clearCart(String email, String password) {
//         User user = userRepo.findByEmailAndPassword(email, password);
//         Cart cart = cartRepo.findByUser(user).orElseGet(() -> createNewCart(user));
//         // Clear the cart
//         cart.getCartItems().clear();
//         cartRepo.save(cart);
//         return "Cart cleared successfully";
//     }

//     public String getUser(String email, String password) {
//         User user = userRepo.findByEmailAndPassword(email, password);
//         try {
//             ObjectMapper objectMapper = new ObjectMapper();
//             return objectMapper.writeValueAsString(user);
//         } catch (JsonProcessingException e) {
//             e.printStackTrace(); // Print the exception details for debugging
//             return "Error converting User to JSON: " + e.getMessage();
//         }
//     }

//     public Product updateProduct(Long id, Product updatedProduct) throws Exception{
//         Optional<Product> existingProduct = productRepo.findById(id);

//         if(existingProduct.isPresent()){
//             Product productToUpdate = existingProduct.get();

//             //Update fields of productToUpadate
//             productToUpdate.setImage(updatedProduct.getName());
//             productToUpdate.setPrice(updatedProduct.getPrice());
//             productToUpdate.setImage(updatedProduct.getImage());
//             productToUpdate.setDescription(updatedProduct.getDescription());

//             return productRepo.save(productToUpdate);
//         }else{
//             throw new Exception("Not Found");
//         }
        
//     }

//     public boolean deleteProduct(Long productId){
//         if(productRepo.existsById(productId)){
//             productRepo.deleteById(productId);
//             return true;
//         }else{
//             return false;
//         }
//     }

}
