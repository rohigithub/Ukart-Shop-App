package com.pro.ukart.Dtos;
import java.util.List;
import java.math.BigDecimal;

public class CartDto {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private List<CartItemDto> cartItems;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public List<CartItemDto> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
    
}
