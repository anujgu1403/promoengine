
package com.promoexecution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promoexecution.model.cart.Cart;
import com.promoexecution.service.PromotionExecutionService;

import reactor.core.publisher.Mono;

/**
 * Promotion controller implementation class
 */
@RestController
public class PromotionControllerImpl implements PromotionController {

	@Autowired
	PromotionExecutionService promotionExecutionService;

	/**
	 *
	 * @param cart
	 * @return
	 */
	@Override
	public Mono<Cart> executePromotion(Cart cart) {
		return promotionExecutionService.executePromotions(cart);
	}
}
