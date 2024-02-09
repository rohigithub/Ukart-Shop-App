package com.pro.ukart.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="mycart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;


    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems= new ArrayList<>();

    @Column(name="total_price")
    private BigDecimal totalPrice;

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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // private boolean isCartItemInCart(CartItem cartItem) {
    //     for (CartItem i : this.cartItems) {
    //         if (cartItem.getProduct() == i.getProduct()) {
    //             return true;
    //         }

    //     }
    //     return false;
    // }

    // public void addCartItem(CartItem cartItem) {
    //     if (!isCartItemInCart(cartItem)) {
    //         cartItem.setCart(this);
    //         this.cartItems.add(cartItem);
    //     }
    //     // if item already in cart increase qty +1
    //     else {
    //         for (CartItem i : this.cartItems) {
    //             if (cartItem.getProduct() == i.getProduct()) {
    //                 i.setQuantity(i.getQuantity() + 1);
    //             }
    //         }
    //     }
    // }

    // public void updateItemQuantity(CartItem cartItem, int quantity) {
    //     for (CartItem i : this.cartItems) {
    //         if (cartItem.getProduct() == i.getProduct()) {
    //             i.setQuantity(quantity);
    //         }

    //     }

    // }

    // public void removeCartItem(CartItem cartItem) {
    //     for (int i = 0; i < this.cartItems.size(); i++) {
    //         if (cartItems.get(i).getProduct() == cartItem.getProduct()) {
    //             cartItems.remove(i);
    //         }
    //     }
    // }

    // public void calculateTotal() {
    //     this.totalPrice = cartItems.stream()
    //             .mapToInt(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
    //             .sum();
    // }

}
