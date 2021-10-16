package com.example.dogukanaligundoganh3.model.interfaces;

import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;

public interface Discount {
    void applyDiscount(CheckoutItem CheckoutItem);
    boolean isAcceptable(CheckoutItem CheckoutItem);
}
