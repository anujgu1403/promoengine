
package com.promoexecution.promorules;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;
import java.util.stream.Collectors;

import com.promoexecution.common.abstraction.BasePromotionRules;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.cart.CartItem;
import com.promoexecution.model.promotion.Promotion;

import lombok.Builder;
import lombok.Data;

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
		BigDecimal itemCount = cart.getCartItems()
				.stream()
				.filter(cartItem -> eligibleSkuId.equals(cartItem.getProductInfo().getSkuId()))
				.map(CartItem::getRemainingQty)
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		return cart.getCartItems()
		        .stream()
		        .anyMatch(cartItem -> (null == cartItem.getPromotionInfo()
		                || !cartItem.getPromotionInfo()
		                        .isPromotionApplied())
		                && eligibleSkuId.equals(cartItem.getProductInfo()
		                        .getSkuId()) && itemCount.intValue()>=noOfItems.intValue());
	}

	@Override
	public Cart execute(Cart cart, Promotion promotion) {
		var discountedPricePerUnit = discountedPrice.divide(noOfItems, MathContext.DECIMAL32);


		if (isApplicable(cart)) {
			Set<CartItem> items = cart.getCartItems()
			        .stream()
			        .filter(cartItem -> (null == cartItem.getPromotionInfo()
			                || !cartItem.getPromotionInfo()
			                        .isPromotionApplied())
			                && (eligibleSkuId.equals(cartItem.getProductInfo()
			                        .getSkuId())))
			        .map(cartItem -> {
				        var discountPerUnit = cartItem.getProductInfo()
				                .getUnitPrice()
				                .subtract(discountedPricePerUnit);

						BigDecimal[] result = cartItem.getQuantity().divideAndRemainder(noOfItems);
						promotion.setPromoAppliedQty(noOfItems.multiply(result[0]));
						cartItem.setRemainingQty(cartItem.getQuantity().subtract(noOfItems.multiply(result[0])));
				        // Build the promotion object and map it into cart item
				        cartItem.setPromotionInfo(buildPromotion(promotion, discountPerUnit));
						return cartItem;
			        }).collect(Collectors.toSet());
			cart.getCartItems().removeAll(items);
		}
		return cart;
	}

	private Promotion buildPromotion(Promotion promotion, BigDecimal discountPerUnit) {
		return Promotion.builder()
		        .promotionId(promotion.getPromotionId())
		        .isPromotionApplied(true)
		        .discountPerUnit(discountPerUnit)
				.promoAppliedQty(promotion.getPromoAppliedQty())
		        .description(promotion.getDescription())
				.isActive(promotion.isActive())
		        .build();
	}
}
