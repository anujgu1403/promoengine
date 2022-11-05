
package com.promoexecution.common.operations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.cart.CartSummary;

public final class CartTotalsOperations {
	public static Cart refreshCartTotals(Cart cart) {

		var subTotal = calculateSubTotal(cart);
		var totalDiscount = calculateTotalDiscount(cart);
		cart.setCartSummary(CartSummary.builder()
		        .subTotal(subTotal)
		        .totalDiscount(totalDiscount.setScale(0, RoundingMode.HALF_EVEN))
		        .grandTotal(subTotal.subtract(totalDiscount).setScale(0, RoundingMode.CEILING))
		        .build());

		return cart;
	}

	private static BigDecimal calculateSubTotal(Cart cart) {
		return Optional.ofNullable(cart)
		        .flatMap(cart1 -> cart1.getCartItems()
		                .stream()
		                .map(cartItem -> cartItem.getProductInfo()
		                        .getUnitPrice()
		                        .multiply(cartItem.getQuantity()))
		                .reduce(BigDecimal::add))
		        .orElse(BigDecimal.ZERO);

	}

	private static BigDecimal calculateTotalDiscount(Cart cart) {
		return Optional.ofNullable(cart)
		        .flatMap(cart1 -> cart1.getCartItems()
		                .stream()
		                .filter(cartItem -> (null != cartItem.getPromotionInfo()
								&& cartItem.getPromotionInfo()
								.isPromotionApplied()))
		                .map(cartItem -> cartItem.getPromotionInfo()
		                        .getDiscountPerUnit()
		                        .multiply(cartItem.getQuantity()))
		                .reduce(BigDecimal::add))
		        .orElse(BigDecimal.ZERO);

	}
}
