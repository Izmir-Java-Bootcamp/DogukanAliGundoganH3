package com.example.dogukanaligundoganh3.service.payment.factory;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.interfaces.Payment;
import com.example.dogukanaligundoganh3.type.PaymentType;
import org.springframework.stereotype.Service;


@Service
public class Cash implements Payment {
    @Override
    public void pay(Checkout checkout) {
        System.out.println("You will pay total "+checkout.getTotalPrice());
    }

    @Override
    public PaymentType getType() {
        return PaymentType.CASH;
    }
}
