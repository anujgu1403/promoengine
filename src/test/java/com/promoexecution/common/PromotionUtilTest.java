package com.promoexecution.common;

import com.promoexecution.common.constant.PromotionConstants;
import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.cart.CartItem;
import com.promoexecution.model.cart.ProductInfo;
import com.promoexecution.model.promotion.Promotion;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Common class to define mock objects
 */
public class PromotionUtilTest {

    public static Cart buildCartScenario1(){
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(CartItem.builder()
                .cartItemId("4343323231")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("A")
                        .name("A Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(50))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323232")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("B")
                        .name("B Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(30))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323233")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("C")
                        .name("C Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(20))
                        .build())
                .build());
        return Cart.builder()
                .cartId("467t43467823")
                .cartItems(cartItems)
                .build();
    }
    public static Cart buildCartScenario2(){
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(CartItem.builder()
                .cartItemId("4343323231")
                .quantity(BigDecimal.valueOf(5))
                .remainingQty(BigDecimal.valueOf(5))
                .productInfo(ProductInfo.builder()
                        .skuId("A")
                        .name("A Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(50))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323232")
                .quantity(BigDecimal.valueOf(5))
                .remainingQty(BigDecimal.valueOf(5))
                .productInfo(ProductInfo.builder()
                        .skuId("B")
                        .name("B Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(30))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323233")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("C")
                        .name("C Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(20))
                        .build())
                .build());
        return Cart.builder()
                .cartId("467t43467823")
                .cartItems(cartItems)
                .build();
    }
    public static Cart buildCartScenario3(){
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(CartItem.builder()
                .cartItemId("4343323231")
                .quantity(BigDecimal.valueOf(3))
                .remainingQty(BigDecimal.valueOf(3))
                .productInfo(ProductInfo.builder()
                        .skuId("A")
                        .name("A Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(50))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323232")
                .quantity(BigDecimal.valueOf(5))
                .remainingQty(BigDecimal.valueOf(5))
                .productInfo(ProductInfo.builder()
                        .skuId("B")
                        .name("B Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(30))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323233")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("C")
                        .name("C Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(20))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323234")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("D")
                        .name("D Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(15))
                        .build())
                .build());
        return Cart.builder()
                .cartId("467t43467823")
                .cartItems(cartItems)
                .build();
    }
    public static Cart buildCartScenario4(){
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(CartItem.builder()
                .cartItemId("4343323231")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("A")
                        .name("A Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(50))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323232")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("A")
                        .name("A Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(50))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323233")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("A")
                        .name("A Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(50))
                        .build())
                .build());
        return Cart.builder()
                .cartId("467t43467823")
                .cartItems(cartItems)
                .build();
    }

    public static Cart buildCartScenario5(){
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(CartItem.builder()
                .cartItemId("4343323233")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("C")
                        .name("C Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(20))
                        .build())
                .build());
        cartItems.add(CartItem.builder()
                .cartItemId("4343323234")
                .quantity(BigDecimal.ONE)
                .remainingQty(BigDecimal.ONE)
                .productInfo(ProductInfo.builder()
                        .skuId("D")
                        .name("D Product")
                        .description("Water heater")
                        .unitPrice(BigDecimal.valueOf(15))
                        .build())
                .build());
        return Cart.builder()
                .cartId("467t43467823")
                .cartItems(cartItems)
                .build();
    }
    public static Promotion buildCombinedItemPromotion(){
        return  Promotion.builder()
                .promotionId("1232321")
                .description(PromotionConstants.COMBINED_ITEMS_PRICE_PROMO)
                .isActive(true)
                .build();
    }
    public static Promotion buildNAItemsPromotion(){
        return  Promotion.builder()
                .promotionId("1232322")
                .description(PromotionConstants.N_A_ITEMS_PRICE_PROMO)
                .isActive(true)
                .build();
    }
    public static Promotion buildNBItemsPromotion(){
        return  Promotion.builder()
                .promotionId("1232323")
                .description(PromotionConstants.N_B_ITEMS_PRICE_PROMO)
                .isActive(true)
                .build();
    }
}
