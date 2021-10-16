package com.example.dogukanaligundoganh3.service.menu;


import com.example.dogukanaligundoganh3.model.category.Category;
import com.example.dogukanaligundoganh3.model.category.Product;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;
import com.example.dogukanaligundoganh3.model.interfaces.Payment;
import com.example.dogukanaligundoganh3.model.menu.Menu;
import com.example.dogukanaligundoganh3.service.payment.factory.Factory;
import com.example.dogukanaligundoganh3.service.product.CheckoutService;
import com.example.dogukanaligundoganh3.type.CategoryType;
import com.example.dogukanaligundoganh3.type.PaymentType;
import com.example.dogukanaligundoganh3.type.SubCategoryType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class MenuService {
    private final Factory factory;
    private final CheckoutService checkoutService;
    private boolean isSearchSucceeded=false;
    private final Menu menu;
    private final Scanner scanner;
    private final List<String> categoryList;
    private final List<String> subCategoryList;
    private final List<Category<Product,SubCategoryType>> subCategories;

    private final List<Category<String, CategoryType>> categories;
    public MenuService(Factory factory, CheckoutService checkoutService, Menu menu, Scanner scanner, @Qualifier("getCategoryList") List<String> categoryList, @Qualifier("getSubCategoryList") List<String> subCategoryList, List<Category<Product, SubCategoryType>> subCategories, List<Category<String, CategoryType>> categories) {
        this.factory = factory;
        this.checkoutService = checkoutService;
        this.menu = menu;
        this.scanner = scanner;
        this.categoryList = categoryList;
        this.subCategoryList = subCategoryList;
        this.subCategories = subCategories;
        this.categories = categories;
    }


    public boolean startMenu() throws NoSuchFieldException {
        createMenu();
        getMenuSelectedItem();
        controlMenuSelectedItem();
        return executeCommandFromMenu();
    }

    public CheckoutItem getCheckoutItem(){
        Product product = Product.builder().name(menu.getProductName()).description("deneme").price(100000).build();
        return CheckoutItem.builder().product(product).quantity(1).build();
    }
    public Payment getPaymentMethod(){
        System.out.println("Lutfen bir odeme sekli seciniz \n1.Kredi Karti\n2.Nakit");
        getMenuSelectedItem();
        int selectedMenuItem = Integer.parseInt(menu.getSelectedMenuItem());
        if (selectedMenuItem>2 || selectedMenuItem<1){
            throw new IllegalArgumentException();
        }
        if (selectedMenuItem==1){
            return factory.getPaymentMethod(PaymentType.CREDIT_CARD);
        }
        return factory.getPaymentMethod(PaymentType.CASH);

    }
    public boolean isProductAdded(){
        if (menu.getMenuId()==16*16*16*16){
            return true;
        }else{
            return false;
        }
    }
    public boolean isStopped(){
        return menu.getMenuId()!=-1;
    }
    public void prepareMenu(){
        for (String str :
                categoryList) {
            CategoryType categoryType = getCategoryType(str);
            Category<String,CategoryType> category= new Category<>(str,categoryType);
            categories.add(category);
        }
        for (String str :
                subCategoryList) {
            SubCategoryType subCategoryType = getSubCategoryType(str);
            Category<Product,SubCategoryType> category = new Category(str,subCategoryType);
            subCategories.add(category);
        }

    }
    //bunda string elle degil enum olarak mi verilmeli solide karsi mi
    private void createMenu(){

        if (menu.getMenuId()==2){
            System.out.println("----Main Menu----\n1.Menu\n2.Search");
        }else if (menu.getMenuId()==3){
            System.out.println("Enter tree letter");
        }
        else if (menu.getMenuId()==9){
            isSearchSucceeded=printSearchResults(menu.getSearchLetters());
        }
        else if (menu.getMenuId()==81){
            System.out.println("----Search Menu----\n1.Checkout\n2.Go Back");
        }else if (menu.getMenuId()==4){
            printList(categoryList);
        }else if (menu.getMenuId()==16){
            printList(subCategoryList);
        }else if (menu.getMenuId()==16*16){
            System.out.println("Please enter the name of product");
        }else if (menu.getMenuId()==16*16*16*16){
            System.out.println("----Menu----\n1.Checkout\n2.Go Back");
        }
    }
    private Boolean printSearchResults(String word){
        List<CheckoutItem> items = checkoutService.getProducts(word);
        if (items.isEmpty()){
            System.out.println("Urun Bulunamadi");
            return false;
        }else{
            items.stream().forEach(System.out::println);
            System.out.println("Sira sifirdan basliyor. Ilk listenen oge 0. oge olarak dusunerek lutfen istediginiz" +
                    "ogeyi seciniz");
            return true;
        }
    }
    public CheckoutItem getSearchItem(){
        return checkoutService.getProducts(menu.getSearchLetters()).get(Integer.parseInt(menu.getSelectedMenuItem()));
    }
    public boolean isSearchFailed(){
        if (menu.getMenuId()==9 && isSearchSucceeded==false){
            return true;
        }
        return false;
    }
    private void getMenuSelectedItem(){
        if (!isSearchFailed()){
            menu.setSelectedMenuItem(scanner.nextLine());
        }
    }
    private void controlMenuSelectedItem() throws NoSuchFieldException {
        int number =0;
        if (!isSearchFailed()){

            try {
                number = Integer.parseInt(menu.getSelectedMenuItem());
            }catch (Exception e){
                if (menu.getMenuId()!=3&&menu.getMenuId()!=16*16){
                    throw new IllegalArgumentException();
                }
            }
            switch (menu.getMenuId()){
                case 2:
                    if (menu.getMenuId()>2 || menu.getMenuId()<1){
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                case 3:
                    if (menu.getSelectedMenuItem().length()>3 || menu.getSelectedMenuItem().length()<1){
                        throw new NoSuchFieldException();
                    }
                    menu.setSearchLetters(menu.getSelectedMenuItem());
                    break;
                case 4:
                    if (number>categoryList.size() || number<0){
                        throw new IndexOutOfBoundsException();
                    }
                    menu.setCategoryType(categories.get(number).getType());
                case 9:
                    menu.setSelectedMenuItem(String.valueOf(number));
                    break;
                case 81:
                case 16*16*16*16:
                    break;
                case 16:
                    if (number>subCategoryList.size() || number<0){
                        throw new IndexOutOfBoundsException();
                    }
                    menu.setSubCategoryType(subCategories.get(number).getType());
                    break;
                case 16*16:
                    menu.setProductName(menu.getSelectedMenuItem());
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }
    private boolean executeCommandFromMenu(){
        if (menu.getMenuId()==2){
            if (Integer.parseInt(menu.getSelectedMenuItem())==1){
                //ana menuye git
                menu.setMenuId((int) Math.pow(menu.getMenuId(),2));
            }else if (Integer.parseInt(menu.getSelectedMenuItem())==2){
                //arama menusune git
                menu.setMenuId(menu.getMenuId()+1);
            }else {
                throw new IllegalArgumentException();
            }

        }
        else if (menu.getMenuId()==81 || menu.getMenuId()==16*16*16*16){
            if (Integer.parseInt(menu.getSelectedMenuItem())==1){
                menu.setMenuId(-1);
                //odeme al
            }else if (Integer.parseInt(menu.getSelectedMenuItem())==2){
                menu.setMenuId(2);
                //geri don
            }else {
                throw new IllegalArgumentException();
            }
            return false;
        }
        else if (isSearchFailed()){
            menu.setMenuId(2);
        }
        else if (menu.getMenuId()<=16*16 && menu.getMenuId()>=3){
            //pipe da ileri git

            menu.setMenuId((int) Math.pow(menu.getMenuId(),2));
            return menu.getMenuId() == 16 * 16 * 16 * 16 || menu.getMenuId() == 27;
        }else{
            throw new IllegalArgumentException();
        }
        return false;
    }
    private void printList(List<String> list){
        for (String str :
                list){
            System.out.println(list.indexOf(str)+". "+str);
        }
    }



    //burayi nasil generic yaparsin sor
    private CategoryType getCategoryType(String type){
        switch (type.toUpperCase()){
            case "FRUIT_AND_VEGETABLE":
                return CategoryType.FRUIT_AND_VEGETABLE;
            case "SNACK":
                return CategoryType.SNACK;
            case "BREAKFAST":
                return CategoryType.BREAKFAST;
            default:
                return CategoryType.UNKNOWN;
        }
    }
    private SubCategoryType getSubCategoryType(String type){
        switch (type.toUpperCase()){
            case "FRUIT":
                return SubCategoryType.FRUIT;
            case "VEGETABLE":
                return SubCategoryType.VEGETABLE;
            case "CHIPS":
                return SubCategoryType.CHIPS;
            case "CHOCOLATE":
                return SubCategoryType.CHOCOLATE;
            case "MILK":
                return SubCategoryType.MILK;
            case "DELICATESSEN":
                return SubCategoryType.DELICATESSEN;
            default:
                return SubCategoryType.UNKNOWN;
        }
    }

}
