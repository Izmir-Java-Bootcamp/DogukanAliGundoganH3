package com.example.dogukanaligundoganh3;

import com.example.dogukanaligundoganh3.model.interfaces.Payment;
import com.example.dogukanaligundoganh3.service.discount.Buy2Take3Discount;
import com.example.dogukanaligundoganh3.service.discount.DiscountService;
import com.example.dogukanaligundoganh3.service.menu.MenuService;
import com.example.dogukanaligundoganh3.service.payment.factory.Factory;
import com.example.dogukanaligundoganh3.service.product.CheckoutService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesScan()
public class StartApp implements CommandLineRunner {

    private final MenuService menuService;
    private final CheckoutService checkoutService;
    private final DiscountService discountService;


    private final Buy2Take3Discount buy2Take3Discount;

    public StartApp(MenuService menuService, CheckoutService checkoutService, DiscountService discountService, Buy2Take3Discount buy2Take3Discount) {
        this.menuService = menuService;
        this.checkoutService = checkoutService;
        this.discountService = discountService;
         this.buy2Take3Discount = buy2Take3Discount;
    }

    @Override
    public void run(String... args) throws Exception {
        menuService.prepareMenu();
        while (menuService.isStopped()){
            boolean flag = menuService.startMenu();
            if (flag){
                if (menuService.isProductAdded()){
                    checkoutService.addCheckoutItem(menuService.getCheckoutItem());
                }else{
                    checkoutService.addCheckoutItem(menuService.getSearchItem());
                }

            }
        }
        discountService.makeDiscount(checkoutService.getCheckout());
        Payment payment = menuService.getPaymentMethod();
        payment.pay(checkoutService.getCheckout());

    }



}
