package com.example.dogukanaligundoganh3.model.checkout;


import com.example.dogukanaligundoganh3.model.category.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutItem {
    //data anatasyonunda set methodu elle override edilir mi ?
    private Product product;
    private int quantity;

    public CheckoutItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
