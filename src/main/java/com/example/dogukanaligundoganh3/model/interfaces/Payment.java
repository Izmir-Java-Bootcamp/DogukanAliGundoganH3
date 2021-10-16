package com.example.dogukanaligundoganh3.model.interfaces;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.type.PaymentType;


public interface Payment {

    void pay(Checkout checkout);
    PaymentType getType();
}
