package com.promoexecution.repository;

import com.promoexecution.common.constant.PromotionConstants;
import com.promoexecution.model.promotion.Promotion;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.UUID;

/**
 *  Repository class to get list of active promotions.
 *  List of promotion can be get from database or using any API call
 */
@Getter
@Component
public class PromotionRepository {
    Set<String> promoEligibleSkuIds = new HashSet();
    public PromotionRepository(Set<String> skuIds){
        promoEligibleSkuIds.addAll(skuIds);
    }

    /**
     *
     * @return Map<String, Promotion>
     */
    public  Map<String, Promotion> getActivePromotions(){
        Map<String, Promotion> promotionList = new HashMap<>();
        promotionList.put(PromotionConstants.N_A_ITEMS_PRICE_PROMO, Promotion.builder()
                        .promotionId(UUID.randomUUID().toString())
                        .description(PromotionConstants.N_A_ITEMS_PRICE_PROMO)
                        .isActive(true)
                .build());
        promotionList.put(PromotionConstants.N_B_ITEMS_PRICE_PROMO,Promotion.builder()
                .promotionId(UUID.randomUUID().toString())
                .description(PromotionConstants.N_B_ITEMS_PRICE_PROMO)
                .isActive(true)
                .build());
        promotionList.put(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO,Promotion.builder()
                .promotionId(UUID.randomUUID().toString())
                .description(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO)
                .isActive(true)
                .build());
        return promotionList;
    }
}