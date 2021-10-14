package com.example.dogukanaligundoganh3.model.checkout;

import com.example.dogukanaligundoganh3.model.interfaces.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Checkout {
    private List<CheckoutItem> checkoutItems;
    private double totalPrice;

}
