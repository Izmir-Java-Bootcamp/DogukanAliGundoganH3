package com.example.dogukanaligundoganh3;

import com.example.dogukanaligundoganh3.model.category.Category;
import com.example.dogukanaligundoganh3.model.category.Product;
import com.example.dogukanaligundoganh3.model.menu.Menu;
import com.example.dogukanaligundoganh3.service.Buy2Take3Discount;
import com.example.dogukanaligundoganh3.type.CategoryType;
import com.example.dogukanaligundoganh3.type.SubCategoryType;
import com.sun.xml.internal.ws.api.pipe.PipelineAssembler;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@ConfigurationPropertiesScan()
public class StartApp implements CommandLineRunner {

    private final Menu menu;
    private final Scanner scanner;
    private final List<String> categoryList;
    private final List<String> subCategoryList;
    private final List<Category<Product,SubCategoryType>> subCategories;

    private final List<Category<String, CategoryType>> categories;

    private final Buy2Take3Discount buy2Take3Discount;

    public StartApp(Menu menu, Scanner scanner, @Qualifier("getCategoryList") List<String> categoryList, @Qualifier("getSubCategoryList") List<String> subCategoryList, List<Category<Product, SubCategoryType>> subCategories, List<Category<String, CategoryType>> categories, Buy2Take3Discount buy2Take3Discount) {
        this.menu = menu;
        this.scanner = scanner;
        this.categoryList = categoryList;
        this.subCategoryList=subCategoryList;
        this.subCategories = subCategories;
        this.categories = categories;
        this.buy2Take3Discount = buy2Take3Discount;
    }


    @Override
    public void run(String... args) throws Exception {
        startMenu();
        while (menu.getMenuId()!=-1){
            createMenu();
            getMenuSelectedItem();
            controlMenuSelectedItem();
            executeCommandFromMenu();
        }

    }

    //generic tanimlamarda builder nasil kullanilir object olarak donuyor


    private void startMenu(){
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
                break;
            case 4:
                if (number>categoryList.size() || number<0){
                    throw new IndexOutOfBoundsException();
                }
            case 9:
                if (number>2|| number<0){
                    throw new IndexOutOfBoundsException();
                }
                break;
            case 16:
                if (number>subCategoryList.size() || number<0){
                    throw new IndexOutOfBoundsException();
                }
                break;
            case 16*16:
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
                menu.setMenuId((int) Math.pow(menu.getMenuId(),2));
            }else if (Integer.parseInt(menu.getSelectedMenuItem())==2){
                menu.setMenuId(menu.getMenuId()+1);
            }else {
                throw new IllegalArgumentException();
            }

        }
        else if (menu.getMenuId()==9 || menu.getMenuId()==16*16*16*16){
            if (Integer.parseInt(menu.getSelectedMenuItem())==1){
                menu.setMenuId(-1);
            }else if (Integer.parseInt(menu.getSelectedMenuItem())==2){
                menu.setMenuId(2);
            }else {
                throw new IllegalArgumentException();
            }
            return true;
        }
        else if (menu.getMenuId()<=16*16 && menu.getMenuId()>=3){
            menu.setMenuId((int) Math.pow(menu.getMenuId(),2));
        }else{
            throw new IllegalArgumentException();
        }
        return true;
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
