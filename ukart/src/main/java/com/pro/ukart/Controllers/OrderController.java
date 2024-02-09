package com.pro.ukart.Controllers;

import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.pro.ukart.Dtos.CartDto;
import com.pro.ukart.Dtos.OrderDto;
import com.pro.ukart.Entities.Order;
import com.pro.ukart.Entities.Product;
import com.pro.ukart.Repositories.CategoryRepo;
import com.pro.ukart.Repositories.OrderRepository;
import com.pro.ukart.Service.CartService;
import com.pro.ukart.Service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    private final OrderService orderService;
    private final CartService cartService;
    //private final PaymentService paymentService;

    @GetMapping("/{userId}")
    public List<OrderDto> getOrdersByUserId(@PathVariable Long userId, Authentication authentication) {
        return orderService.getOrdersByUserId(userId, authentication);
    }

    /*@PostMapping("/{userId}/checkout")
    public ResponseEntity<PaymentDto> checkout(@PathVariable Long userId, Authentication authentication) throws StripeException {
        CartDto cart = cartService.getCartByUserId(userId);
        BigDecimal totalPrice = cart.getTotalPrice();
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(totalPrice);

        Order createdOrder = orderService.createOrderFromCart(cart, userId, authentication);

        cartService.clearCart(userId);

        PaymentDto paymentDto = new PaymentDto(paymentIntent.getClientSecret(), totalPrice, "usd", createdOrder.getId());

        return ResponseEntity.ok().body(paymentDto);
    }*/

    /*@Autowired
    private OrderRepo orderRepo;

    @GetMapping("/myorders")
    public ResponseEntity<List<Order>> getAllProducts() {
        List<Order> orders = orderRepo.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Optional<Order>> getProduct(@PathVariable("id") Long id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(order, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/place_order")
    public ResponseEntity<Order> addProduct(@RequestBody Order order) {
        Order addedProduct = orderRepo.save(order);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        orderRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }*/

}
