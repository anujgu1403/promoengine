package com.promoexecution.model.cart;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class Cart {
    private String cartId;
    private Set<CartItem> cartItems;
    private CartSummary cartSummary;

}
