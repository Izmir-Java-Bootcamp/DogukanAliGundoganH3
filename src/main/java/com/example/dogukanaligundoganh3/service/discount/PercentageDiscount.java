package com.example.dogukanaligundoganh3.service.discount;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.discount.DiscountProperties;
import com.example.dogukanaligundoganh3.model.interfaces.Discount;
import org.springframework.stereotype.Service;


@Service
public class PercentageDiscount implements Discount {
    private DiscountProperties discountProperties;

    public PercentageDiscount(DiscountProperties discountProperties){
        this.discountProperties=discountProperties;
    }
    @Override
    public void applyDiscount(Checkout checkout) {

    }

    @Override
    public boolean isAcceptable(Checkout checkout) {
        return checkout.getTotalPrice()>1000;
    }
}
