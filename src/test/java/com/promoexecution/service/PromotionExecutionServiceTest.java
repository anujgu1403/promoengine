package com.promoexecution.service;

import com.promoexecution.common.PromotionUtilTest;
import com.promoexecution.common.constant.PromotionConstants;
import com.promoexecution.extension.PromotionInitializer;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.promotion.Promotion;
import com.promoexecution.promorules.CombinedItemPricePromotionRule;
import com.promoexecution.promorules.FixedPricePromotionRule;
import com.promoexecution.repository.PromotionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PromotionExecutionServiceTest {
    @InjectMocks
    PromotionExecutionService promotionExecutionService;
    @MockBean
    PromotionRepository promotionRepository;
    @MockBean
    PromotionInitializer promotionInitializer;
    @MockBean
    FixedPricePromotionRule fixedPricePromotionRule;
    @MockBean
    CombinedItemPricePromotionRule combinedItemPricePromotionRule;


    @Test
    public void validatePromotionScenario1(){

        Map<String, Promotion> promotionMap = new HashMap<>();
        Mockito.when(promotionRepository.getActivePromotions()).thenReturn(promotionMap);
        Mockito.when(promotionInitializer.mapFixedPricePromotionRule(PromotionConstants.N_A_ITEMS_PRICE_PROMO))
                .thenReturn(fixedPricePromotionRule);
        Mockito.when(promotionInitializer.mapCombinedItemPricePromotionRule(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO))
                .thenReturn(combinedItemPricePromotionRule);
        promotionExecutionService.executePromotions(PromotionUtilTest.buildCartScenario1());
        StepVerifier.create(promotionExecutionService.executePromotions(PromotionUtilTest.buildCartScenario1()))
                .assertNext(this::assertCartPromotion)
                .verifyComplete();
    }
    private void assertCartPromotion(Cart cart){
        boolean isPromotionAppliedOnCart = cart.getCartItems()
                .stream()
                .allMatch(cartItem -> null==cartItem.getPromotionInfo());
        Assert.isTrue(isPromotionAppliedOnCart, "Promotion should not be applied on cart");

    }
}
