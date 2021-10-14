package com.example.dogukanaligundoganh3.service.discount;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.interfaces.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DiscountStrategy {
    private final List<Discount> discountList;

    public void makeDiscount(Checkout checkout){
        discountList.stream()
                .filter(discount -> discount.isAcceptable(checkout))
                .findFirst()
                .ifPresent(discount -> discount.applyDiscount(checkout));
    }


}
