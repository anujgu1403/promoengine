
package com.promoexecution.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.promoexecution.model.cart.Cart;

import reactor.core.publisher.Mono;

public interface PromotionController {

	@PostMapping(value = "/execution", consumes = {MediaType.APPLICATION_JSON_VALUE},
	             produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<Cart> executePromotion(@Validated @RequestBody Cart cart);
}
