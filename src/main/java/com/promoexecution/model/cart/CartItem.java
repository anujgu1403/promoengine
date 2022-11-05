
package com.promoexecution.model.cart;

import java.math.BigDecimal;

import com.promoexecution.model.promotion.Promotion;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItem {
	private String cartItemId;
	private ProductInfo productInfo;
	private Promotion promotionInfo;
	private BigDecimal quantity;
}
