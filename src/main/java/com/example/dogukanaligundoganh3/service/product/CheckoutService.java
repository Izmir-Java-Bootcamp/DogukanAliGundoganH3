package com.example.dogukanaligundoganh3.service.product;


import com.example.dogukanaligundoganh3.model.category.Product;
import com.example.dogukanaligundoganh3.model.checkout.Checkout;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;
import com.example.dogukanaligundoganh3.service.discount.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    private final Checkout checkout;
    private final DiscountStrategy discountStrategy;

    public CheckoutService(Checkout checkout, DiscountStrategy discountStrategy) {
        this.checkout = checkout;
        this.discountStrategy = discountStrategy;
    }

    public Checkout addCheckoutItem(CheckoutItem checkoutItem){
        List<CheckoutItem> items = checkout.getCheckoutItems();

        Optional<CheckoutItem> unwrappedItem = items.stream().filter(c->c.equals(checkoutItem)).findAny();
        if (unwrappedItem.isPresent()){
            CheckoutItem item = unwrappedItem.get();
            item.setQuantity(item.getQuantity()+1);
        }else{
             items.add(checkoutItem);
        }
        return checkout;
    }
    public List<CheckoutItem> getProducts(String word){
        return checkout.getCheckoutItems().stream().filter(product-> product.getProduct().getName().contains(word))
                .collect(Collectors.toList());
    }
    public Checkout getCheckout(){
        return checkout;
    }

}
