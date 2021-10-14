package com.example.dogukanaligundoganh3.service.menu;


import com.example.dogukanaligundoganh3.model.category.Category;
import com.example.dogukanaligundoganh3.model.category.Product;
import com.example.dogukanaligundoganh3.model.checkout.CheckoutItem;
import com.example.dogukanaligundoganh3.model.menu.Menu;
import com.example.dogukanaligundoganh3.service.product.CheckoutService;
import com.example.dogukanaligundoganh3.type.CategoryType;
import com.example.dogukanaligundoganh3.type.SubCategoryType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class MenuService {
    private final Menu menu;
    private final Scanner scanner;
    private final List<String> categoryList;
    private final List<String> subCategoryList;
    private final List<Category<Product,SubCategoryType>> subCategories;

    private final List<Category<String, CategoryType>> categories;
    public MenuService(Menu menu, Scanner scanner, @Qualifier("getCategoryList") List<String> categoryList, @Qualifier("getSubCategoryList") List<String> subCategoryList, List<Category<Product, SubCategoryType>> subCategories, List<Category<String, CategoryType>> categories) {
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
        Product product = Product.builder().name(menu.getProductName()).description("deneme").price(100).build();
        return CheckoutItem.builder().product(product).quantity(1).build();
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
    private void getMenuSelectedItem(){
        menu.setSelectedMenuItem(scanner.nextLine());
    }
    private void controlMenuSelectedItem() throws NoSuchFieldException {
        int number =0;
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
                if (number>2|| number<0){
                    throw new IndexOutOfBoundsException();
                }
                break;
            case 16:
                if (number>subCategoryList.size() || number<0){
                    throw new IndexOutOfBoundsException();
                }
                menu.setSubCategoryType(subCategories.get(number).getType());
                break;
            case 16*16:
                menu.setProductName(menu.getSelectedMenuItem());
                //control yapabilir
                break;
            case 16*16*16*16:
                break;
            default:
                throw new IllegalStateException();
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
        else if (menu.getMenuId()==9 || menu.getMenuId()==16*16*16*16){
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
        else if (menu.getMenuId()<=16*16 && menu.getMenuId()>=3){
            //pipe da ileri git
            menu.setMenuId((int) Math.pow(menu.getMenuId(),2));
            return menu.getMenuId() == 16 * 16 * 16 * 16 || menu.getMenuId() == 9;
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
