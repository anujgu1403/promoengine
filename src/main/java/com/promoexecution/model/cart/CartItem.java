
package com.promoexecution.model.cart;

import java.math.BigDecimal;

import com.promoexecution.model.promotion.Promotion;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
public class CartItem {
	@EqualsAndHashCode.Include
	private String cartItemId;
	private ProductInfo productInfo;
	private Promotion promotionInfo;
	private BigDecimal quantity;
	private BigDecimal remainingQty;
}
