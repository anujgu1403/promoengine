package com.promoexecution.model.cart;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ItemSummary {
    private BigDecimal subTotal;
    private BigDecimal totalSalesTax;
    private BigDecimal totalDiscount;
    private BigDecimal grandTotal;
}
