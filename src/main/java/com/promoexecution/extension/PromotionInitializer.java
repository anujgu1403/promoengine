
package com.promoexecution.extension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.promoexecution.promorules.CombinedItemPricePromotionRule;
import com.promoexecution.promorules.FixedPricePromotionRule;

@Component
public class PromotionInitializer {

	public FixedPricePromotionRule mapFixedPricePromotionRule(String promotion) {

		var promotionDetails = Arrays.stream(promotion.split(" "))
		        .filter(promo -> !"for".equals(promo) && !"of".equals(promo))
		        .collect(Collectors.toList());
		var skuIds = promotionDetails.get(1)
		        .replace("'s", "");
		var noOfItems = BigDecimal.valueOf(Long.parseLong(promotionDetails.get(0)));
		var discountedPrice
		    = BigDecimal.valueOf(Long.parseLong(promotionDetails.get(promotionDetails.size() - 1)));
		return FixedPricePromotionRule.builder()
		        .eligibleSkuId(skuIds)
		        .noOfItems(noOfItems)
		        .discountedPrice(discountedPrice)
		        .build();

	}

	public CombinedItemPricePromotionRule mapCombinedItemPricePromotionRule(String promotion) {
		var promotionDetails = Arrays.stream(promotion.split(" "))
		        .filter(promo -> !"for".equals(promo) && !"&".equals(promo))
		        .collect(Collectors.toList());

		var skuIds = promotionDetails.subList(0, promotionDetails.size() - 1);
		var discountedPrice
		    = BigDecimal.valueOf(Long.parseLong(promotionDetails.get(promotionDetails.size() - 1)));
		return new CombinedItemPricePromotionRule(skuIds,discountedPrice);
	}
}
