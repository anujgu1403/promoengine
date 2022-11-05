package com.promoexecution.model.cart;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProductInfo {
    private String skuId;
    private String name;
    private String description;
    private BigDecimal unitPrice;
}
