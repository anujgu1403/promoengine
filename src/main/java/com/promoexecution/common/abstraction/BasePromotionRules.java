package com.promoexecution.common.abstraction;

import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.promotion.Promotion;
import com.promoexecution.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Base class to define methods which can have different implementation based of different type of promotion rules
 */
public abstract class BasePromotionRules implements PromotionRule{

    @Autowired
    protected PromotionRepository promotionRepository;

    private String promoRuleName;

    /**
     *
     * @param cart
     * @return
     */
   public boolean isCartEmpty(Cart cart){
       return CollectionUtils.isEmpty(cart.getCartItems());
   }

    /**
     *
     * @param cart
     * @return
     */
   public abstract boolean isApplicable(Cart cart);

    /**
     *
     * @param cart
     * @param promotion
     * @return
     */
   public abstract Cart execute(Cart cart, Promotion promotion);
}
