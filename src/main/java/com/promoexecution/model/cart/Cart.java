package com.promoexecution.model.cart;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Builder
@Data
public class Cart {
    @EqualsAndHashCode.Include
    private String cartId;
    private Set<CartItem> cartItems;
    private CartSummary cartSummary;

}
