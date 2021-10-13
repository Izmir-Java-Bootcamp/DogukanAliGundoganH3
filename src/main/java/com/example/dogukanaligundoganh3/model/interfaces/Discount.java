package com.example.dogukanaligundoganh3.model.interfaces;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;

public interface Discount {
    void applyDiscount(Checkout checkout);
    boolean isAcceptable(Checkout checkout);
}
