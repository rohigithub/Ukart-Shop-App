package com.pro.ukart.Mappers;

import java.math.BigDecimal;
import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.pro.ukart.Dtos.CartDto;
import com.pro.ukart.Dtos.CartItemDto;
import com.pro.ukart.Entities.Cart;
import com.pro.ukart.Entities.CartItem;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    @Mapping(target = "id", source = "cart.id")
    @Mapping(target = "cartItems", source = "cartItems")
    CartDto cartToCartDto(Cart cart, BigDecimal totalPrice, List<CartItemDto> cartItems);

    @Mapping(target= "subTotal", expression= "java(cartItem.getSubTotal())")
    CartItemDto cartItemToCartItemDto(CartItem cartItem);
    
    
}
