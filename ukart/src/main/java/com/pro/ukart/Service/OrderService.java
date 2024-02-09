package com.pro.ukart.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pro.ukart.Dtos.CartDto;
import com.pro.ukart.Dtos.CartItemDto;
import com.pro.ukart.Dtos.OrderDto;
import com.pro.ukart.Entities.Order;
import com.pro.ukart.Entities.OrderItem;
import com.pro.ukart.Entities.Product;
import com.pro.ukart.Entities.User;
import com.pro.ukart.Exceptions.AppException;
import com.pro.ukart.Repositories.OrderRepository;
import com.pro.ukart.Repositories.ProductRepository;
import com.pro.ukart.Repositories.UserRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDto> getOrdersByUserId(Long userId, Authentication authentication) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found.", HttpStatus.NOT_FOUND));

        if (authentication == null || !user.getEmail().equals(authentication.getName())) {
            throw new AppException("Access denied.", HttpStatus.BAD_REQUEST);
        }

        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setTotal(order.getTotal());
            String dateCreatedStr = dateFormat.format(order.getDateCreated());
            orderDto.setDateCreated(dateCreatedStr);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public Order createOrderFromCart(CartDto cart, Long userId, Authentication authentication) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (authentication == null || !user.getEmail().equals(authentication.getName())) {
            throw new AppException("Access denied.", HttpStatus.BAD_REQUEST);
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotal(cart.getTotalPrice());
        order.setDateCreated(new Date());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemDto cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

}
