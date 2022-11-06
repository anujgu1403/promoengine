package com.promoexecution.promorules;

import com.promoexecution.common.PromotionUtilTest;
import com.promoexecution.model.cart.Cart;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.math.BigDecimal;

/**
 *  Test class to validate fixed promotions
 */
@SpringBootTest
public class FixedPricePromotionRuleTest {
    @InjectMocks
    FixedPricePromotionRule fixedPricePromotionRule;

    @Test
    public void validateFixedPricePromotion(){
        fixedPricePromotionRule.setNoOfItems(BigDecimal.valueOf(3));
        fixedPricePromotionRule.setDiscountedPrice(BigDecimal.valueOf(130));
        fixedPricePromotionRule.setEligibleSkuId("A");

        Cart cart = fixedPricePromotionRule.execute(PromotionUtilTest.buildCartScenario1(),PromotionUtilTest.buildNAItemsPromotion());
        boolean isPromotionAppliedOnCart = cart.getCartItems()
                .stream()
                .allMatch(cartItem -> null==cartItem.getPromotionInfo());
        Assert.isTrue(isPromotionAppliedOnCart, "Promotion should not be applied on cart");
    }

    @Test
    public void validateFixedPricePromotionForSameItem(){
        fixedPricePromotionRule.setNoOfItems(BigDecimal.valueOf(3));
        fixedPricePromotionRule.setDiscountedPrice(BigDecimal.valueOf(130));
        fixedPricePromotionRule.setEligibleSkuId("A");

        Cart cart = fixedPricePromotionRule.execute(PromotionUtilTest.buildCartScenario4(),PromotionUtilTest.buildNAItemsPromotion());
        boolean isPromotionAppliedOnCart = cart.getCartItems()
                .stream()
                .allMatch(cartItem -> null!=cartItem.getPromotionInfo());
        Assert.isTrue(isPromotionAppliedOnCart, "Promotion should be applied on cart");
    }
}
