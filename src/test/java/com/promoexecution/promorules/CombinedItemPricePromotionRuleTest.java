package com.promoexecution.promorules;

import com.promoexecution.common.PromotionUtilTest;
import com.promoexecution.common.constant.PromotionConstants;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.promotion.Promotion;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Test class to validate combined items price promotion
 */
@SpringBootTest
public class CombinedItemPricePromotionRuleTest {

    @InjectMocks
    CombinedItemPricePromotionRule combinedItemPricePromotionRule;

    @Test
    public void validateCombinedItemPricePromotion(){
        combinedItemPricePromotionRule.setDiscountedPrice(BigDecimal.valueOf(30));
        combinedItemPricePromotionRule.setEligibleSkuIds(Arrays.asList("C","D"));

        Promotion promotion = Promotion.builder()
                .promotionId("1232321")
                .description(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO)
                .isActive(true)
                .build();
        Cart cart = combinedItemPricePromotionRule.execute(PromotionUtilTest.buildCartScenario5(), promotion);
        boolean isPromotionAppliedOnCart = cart.getCartItems()
                .stream()
                .allMatch(cartItem -> null!=cartItem.getPromotionInfo());
        Assert.isTrue(isPromotionAppliedOnCart, "Promotion should be applied on cart");
    }
}
