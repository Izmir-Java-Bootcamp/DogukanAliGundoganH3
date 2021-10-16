package com.example.dogukanaligundoganh3.service.discount;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    private final DiscountStrategy discountStrategy;

    public DiscountService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }


    public void makeDiscount(Checkout checkout){
        discountStrategy.makeDiscount(checkout);
    }
}
