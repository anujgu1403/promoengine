
package com.promoexecution.promorules;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.promoexecution.model.cart.CartItem;
import org.springframework.util.CollectionUtils;

import com.promoexecution.common.abstraction.BasePromotionRules;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.promotion.Promotion;

import lombok.Builder;
import lombok.Data;

/**
 * Extension class of BasePromotionRule to define rule for combined items type promotion
 * e.g. C & D for 30
 */
@Builder
@Data
public class CombinedItemPricePromotionRule extends BasePromotionRules {

	private List<String> eligibleSkuIds;
	private BigDecimal discountedPrice;

	public CombinedItemPricePromotionRule(List<String> eligibleSkuIds, BigDecimal discountedPrice) {
		this.eligibleSkuIds = eligibleSkuIds;
		this.discountedPrice = discountedPrice;
	}

	/**
	 *
	 * @param cart
	 * @return
	 */
	@Override
	public boolean isApplicable(Cart cart) {
		boolean isPromoApplicable = false;
		if (isCartEmpty(cart))
			return false;
		List<String> cartSkuIds = cart.getCartItems()
		        .stream()
		        .filter(cartItem -> (null == cartItem.getPromotionInfo()
		                || !cartItem.getPromotionInfo()
		                        .isPromotionApplied()))
		        .map(cartItem -> cartItem.getProductInfo()
		                .getSkuId())
		        .collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(cartSkuIds)) {
			isPromoApplicable = cartSkuIds.containsAll(eligibleSkuIds);
		}
		return isPromoApplicable;
	}

	/**
	 *
	 * @param cart
	 * @param promotion
	 * @return
	 */
	@Override
	public Cart execute(Cart cart, Promotion promotion) {
		var discountedPricePerUnit
		    = discountedPrice.divide(BigDecimal.valueOf(eligibleSkuIds.size()), MathContext.DECIMAL32);
		if (isApplicable(cart)) {
			Set<CartItem> items = cart.getCartItems()
			        .stream()
			        .filter(cartItem -> (null == cartItem.getPromotionInfo()
			                || !cartItem.getPromotionInfo()
			                        .isPromotionApplied())
							&& eligibleSkuIds.contains(cartItem.getProductInfo().getSkuId()))
			        .map(cartItem -> {
				        var discountPerUnit = cartItem.getProductInfo()
				                .getUnitPrice()
				                .subtract(discountedPricePerUnit);

						//Calculate promotion applicable quantity and map into promotion object
						promotion.setPromoAppliedQty(cartItem.getQuantity());

				        // Build the promotion object and map it into cart item
				        cartItem.setPromotionInfo(buildPromotion(promotion, discountPerUnit));
				        return cartItem;
			        }).collect(Collectors.toSet());
			cart.getCartItems().removeAll(items);
		}
		return cart;
	}

	/**
	 *
	 * @param promotion
	 * @param discountPerUnit
	 * @return
	 */
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
