
package com.promoexecution.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.promoexecution.model.cart.Cart;

import reactor.core.publisher.Mono;

/**
 * Controller interface to declare the API end apoints
 */
public interface PromotionController {

	/**
	 *
	 * @param cart
	 * @return
	 */
	@PostMapping(value = "/promo/execution", consumes = {MediaType.APPLICATION_JSON_VALUE},
	             produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<Cart> executePromotion(@Validated @RequestBody Cart cart);
}
