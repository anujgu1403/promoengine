package com.promoexecution.model.promotion;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@Data
public class Promotion {
    @EqualsAndHashCode.Include
    private String promotionId;
    private String description;
    private BigDecimal discountPerUnit;
    private BigDecimal promoAppliedQty;
    private boolean isPromotionApplied;
    private boolean isActive;
}
