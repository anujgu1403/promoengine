
package com.promoexecution.promorules;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.promoexecution.common.abstraction.BasePromotionRules;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.cart.CartItem;
import com.promoexecution.model.promotion.Promotion;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Mono;

@Builder
@Data
public class FixedPricePromotionRule extends BasePromotionRules {

	private String eligibleSkuId;
	private BigDecimal noOfItems;
	private BigDecimal discountedPrice;

	@Override
	public boolean isApplicable(Cart cart) {
		if (isCartEmpty(cart))
			return false;
		return cart.getCartItems()
		        .stream()
		        .anyMatch(cartItem -> (null == cartItem.getPromotionInfo()
		                || !cartItem.getPromotionInfo()
		                        .isPromotionApplied())
		                && eligibleSkuId.equals(cartItem.getProductInfo()
		                        .getSkuId()));

	}

	@Override
	public Cart execute(Cart cart, Promotion promotion) {
		var discountedPricePerUnit = discountedPrice.divide(noOfItems, MathContext.DECIMAL32);
		if (isApplicable(cart)) {
			Set<CartItem> items = cart.getCartItems()
			        .stream()
			        .filter(cartItem -> (null == cartItem.getPromotionInfo()
			                || cartItem.getPromotionInfo()
			                        .isPromotionApplied())
			                && eligibleSkuId.equals(cartItem.getProductInfo()
			                        .getSkuId()))
			        .map(cartItem -> {
				        var discountPerUnit = cartItem.getProductInfo()
				                .getUnitPrice()
				                .subtract(discountedPricePerUnit);
				        // Build the promotion object and map it into cart item
				        cartItem.setPromotionInfo(buildPromotion(promotion, discountPerUnit));
						return cartItem;
			        }).collect(Collectors.toSet());
			cart.setCartItems(items);
		}
		return cart;
	}

	private Promotion buildPromotion(Promotion promotion, BigDecimal discountPerUnit) {
		return Promotion.builder()
		        .promotionId(promotion.getPromotionId())
		        .isPromotionApplied(true)
		        .discountPerUnit(discountPerUnit)
		        .description(promotion.getDescription())
		        .build();
	}
}
