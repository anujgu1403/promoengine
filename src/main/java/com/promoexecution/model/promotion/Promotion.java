package com.promoexecution.model.promotion;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Promotion {
    private String promotionId;
    private String description;
    private BigDecimal discountPerUnit;
    private boolean isPromotionApplied;
    private boolean isActive;
}
