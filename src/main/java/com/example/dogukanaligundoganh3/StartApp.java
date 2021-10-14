package com.example.dogukanaligundoganh3;

import com.example.dogukanaligundoganh3.model.menu.Menu;
import com.example.dogukanaligundoganh3.service.discount.Buy2Take3Discount;
import com.example.dogukanaligundoganh3.service.menu.MenuService;
import com.example.dogukanaligundoganh3.service.product.CheckoutService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesScan()
public class StartApp implements CommandLineRunner {

    private final MenuService menuService;
    private final CheckoutService checkoutService;


    private final Buy2Take3Discount buy2Take3Discount;

    public StartApp(MenuService menuService, CheckoutService checkoutService, Buy2Take3Discount buy2Take3Discount) {
        this.menuService = menuService;
        this.checkoutService = checkoutService;
        this.buy2Take3Discount = buy2Take3Discount;
    }

    /*

    **** urunlere aciklama girilecekmi
    **** urunlerin fiyatlari girilecek mi
    **** hazi bilgilerin bulundugu bir sepet olup fiyat vb bilgiler oradan mi alinacak
    **** arama ekraninda urun ciktiktan sonra category secimi olmayacak sadece urun mu secilecek
     */

    @Override
    public void run(String... args) throws Exception {
        menuService.prepareMenu();
        while (menuService.isStopped()){
            boolean flag = menuService.startMenu();
            if (flag){
                if (menuService.isProductAdded()){
                    checkoutService.addCheckoutItem(menuService.getCheckoutItem());
                }
                // else kismina search eklenecek
            }
        }
        checkoutService.makeDiscount();
        checkoutService.makePayment();

    }

    //generic tanimlamarda builder nasil kullanilir object olarak donuyor


}
