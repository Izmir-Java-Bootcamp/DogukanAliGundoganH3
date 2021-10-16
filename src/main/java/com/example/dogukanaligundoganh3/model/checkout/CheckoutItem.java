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

    public void applyBuy2Take3Discount(){
        int totalDiscountAmount=quantity/3;
        int restProductNotEffectedByDiscount=quantity%3;
        product.setPrice((totalDiscountAmount*2*product.getPrice()+product.getPrice()*restProductNotEffectedByDiscount)/quantity);
    }

    @Override
    public String toString() {
        return "CheckoutItem{\n" +
                "product=" + product +
                ", \nquantity=" + quantity +
                '}';
    }
}
