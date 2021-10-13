package com.example.dogukanaligundoganh3.service;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.discount.DiscountProperties;
import com.example.dogukanaligundoganh3.model.interfaces.Discount;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Buy2Take3Discount implements Discount {
    private DiscountProperties discountProperties;

    public Buy2Take3Discount(DiscountProperties discountProperties){
        this.discountProperties=discountProperties;
    }
    @Override
    public void applyDiscount(Checkout checkout) {
        System.out.println(discountProperties.getCreditCartCommission());
    }

    @Override
    public boolean isAcceptable(Checkout checkout) {
        return checkout.getCheckoutItems().size()>5;
    }
}
