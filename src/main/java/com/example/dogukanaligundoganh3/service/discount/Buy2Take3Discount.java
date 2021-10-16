package com.example.dogukanaligundoganh3.service.discount;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;
import com.example.dogukanaligundoganh3.model.discount.DiscountProperties;
import com.example.dogukanaligundoganh3.model.interfaces.Discount;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Buy2Take3Discount implements Discount {
    private DiscountProperties discountProperties;
    public Buy2Take3Discount(DiscountProperties discountProperties){
        this.discountProperties=discountProperties;
    }
    @Override
    public void applyDiscount(CheckoutItem checkoutItem) {

            int itemAmount = checkoutItem.getQuantity()/3;
            int restItem = checkoutItem.getQuantity()%3;
            int price = checkoutItem.getProduct().getPrice();
            checkoutItem.getProduct().setPrice((price*itemAmount*2)+restItem*price);


    }

    @Override
    public boolean isAcceptable(CheckoutItem checkoutItem) {
        return checkoutItem.getQuantity()>=5;
    }
}
