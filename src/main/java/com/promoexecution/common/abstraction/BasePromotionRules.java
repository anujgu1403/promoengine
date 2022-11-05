package com.promoexecution.common.abstraction;

import com.promoexecution.model.cart.Cart;
import com.promoexecution.model.promotion.Promotion;
import com.promoexecution.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

public abstract class BasePromotionRules implements PromotionRule{

    @Autowired
    protected PromotionRepository promotionRepository;

    private String promoRuleName;
   public boolean isCartEmpty(Cart cart){
       return CollectionUtils.isEmpty(cart.getCartItems());
   }

   public abstract boolean isApplicable(Cart cart);
   public abstract Cart execute(Cart cart, Promotion promotion);
}
