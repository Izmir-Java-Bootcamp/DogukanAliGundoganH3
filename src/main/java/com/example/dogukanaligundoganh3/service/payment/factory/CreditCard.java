package com.example.dogukanaligundoganh3.service.payment.factory;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.discount.DiscountProperties;
import com.example.dogukanaligundoganh3.model.interfaces.Payment;
import com.example.dogukanaligundoganh3.type.PaymentType;
import org.springframework.stereotype.Service;


@Service
public class CreditCard implements Payment {
    private final DiscountProperties discountProperties;

    public CreditCard(DiscountProperties discountProperties) {
        this.discountProperties = discountProperties;
    }


    @Override
    public void pay(Checkout checkout) {
        double currentPrice = checkout.getTotalPrice();
        double newPrice = currentPrice+(currentPrice*discountProperties.getCreditCartCommission()/100);
        System.out.println("Komisyon alinmadan once "+ currentPrice +"\nKomisyondan sonra "+newPrice);
        checkout.setTotalPrice(newPrice);
    }

    @Override
    public PaymentType getType() {
        return PaymentType.CREDIT_CARD;
    }
}
