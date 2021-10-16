package com.example.dogukanaligundoganh3.service.discount;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;
import com.example.dogukanaligundoganh3.model.discount.DiscountProperties;
import com.example.dogukanaligundoganh3.model.interfaces.Discount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PercentageDiscount implements Discount {
    private DiscountProperties discountProperties;

    public PercentageDiscount(DiscountProperties discountProperties){
        this.discountProperties=discountProperties;
    }
    @Override
    public void applyDiscount(CheckoutItem checkoutItem) {
        int price = checkoutItem.getProduct().getPrice()*discountProperties.getPercentage()/100;
        price=checkoutItem.getProduct().getPrice()-price;
        checkoutItem.getProduct().setPrice(price);
    }

    @Override
    public boolean isAcceptable(CheckoutItem checkoutItem) {
        return checkoutItem.getProduct().getPrice()*checkoutItem.getQuantity()>1000;
    }
}
