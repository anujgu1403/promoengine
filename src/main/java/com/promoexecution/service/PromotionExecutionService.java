
package com.promoexecution.service;

import com.promoexecution.common.operations.CartTotalsOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promoexecution.common.abstraction.BaseService;
import com.promoexecution.common.constant.PromotionConstants;
import com.promoexecution.extension.PromotionInitializer;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.repository.PromotionRepository;

import reactor.core.publisher.Mono;

/**
 * Service class to orchestrate different types of promotions can be application on any cart.
 */
@Service
public class PromotionExecutionService implements BaseService {
	@Autowired
	PromotionInitializer promotionInitializer;
	@Autowired
	private PromotionRepository promotionRepository;

	/**
	 *
	 * @param cart
	 * @return
	 */
	public Mono<Cart> executePromotions(Cart cart) {
		return Mono.just(cart)
		        .map(cartModel -> promotionInitializer
		                .mapCombinedItemPricePromotionRule(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO)
		                .execute(cartModel, promotionRepository.getActivePromotions()
		                        .get(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO)))
		        .map(cartModel -> promotionInitializer
		                .mapFixedPricePromotionRule(PromotionConstants.N_A_ITEMS_PRICE_PROMO)
		                .execute(cartModel, promotionRepository.getActivePromotions()
		                        .get(PromotionConstants.N_A_ITEMS_PRICE_PROMO)))
		        .map(cartModel -> promotionInitializer
		                .mapFixedPricePromotionRule(PromotionConstants.N_B_ITEMS_PRICE_PROMO)
		                .execute(cartModel, promotionRepository.getActivePromotions()
		                        .get(PromotionConstants.N_B_ITEMS_PRICE_PROMO)))
				.map(CartTotalsOperations::refreshCartTotals);
	}
}
