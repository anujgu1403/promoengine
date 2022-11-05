
package com.promoexecution.promorules;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.promoexecution.common.abstraction.BasePromotionRules;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.promotion.Promotion;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Mono;

@Builder
@Data
public class CombinedItemPricePromotionRule extends BasePromotionRules {

	private List<String> eligibleSkuIds;
	private BigDecimal discountedPrice;

	public CombinedItemPricePromotionRule(List<String> eligibleSkuIds, BigDecimal discountedPrice) {
		this.eligibleSkuIds = eligibleSkuIds;
		this.discountedPrice = discountedPrice;
	}

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
			isPromoApplicable = cartSkuIds.stream()
			        .anyMatch(skuId -> !eligibleSkuIds.contains(skuId));
		}
		return isPromoApplicable;
	}

	@Override
	public Cart execute(Cart cart, Promotion promotion) {
		var discountedPricePerUnit
		    = discountedPrice.divide(BigDecimal.valueOf(eligibleSkuIds.size()));
		if (isApplicable(cart)) {
			Mono.just(cart)
					.map(cartModel->cartModel.getCartItems()
			        .stream()
			        .filter(cartItem -> (null != cartItem.getPromotionInfo()
			                && cartItem.getPromotionInfo()
			                        .isPromotionApplied()))
			        .map(cartItem -> {
				        var discountPerUnit = cartItem.getProductInfo()
				                .getUnitPrice()
				                .subtract(discountedPricePerUnit);
				        // Build the promotion object and map it into cart item
				        cartItem.setPromotionInfo(buildPromotion(promotion, discountPerUnit));
				        return cartItem;
			        }));
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
