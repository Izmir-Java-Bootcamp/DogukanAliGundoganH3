package com.example.dogukanaligundoganh3.configuration;


import com.example.dogukanaligundoganh3.model.category.Category;
import com.example.dogukanaligundoganh3.model.category.Product;
import com.example.dogukanaligundoganh3.model.menu.Menu;
import com.example.dogukanaligundoganh3.type.CategoryType;
import com.example.dogukanaligundoganh3.type.SubCategoryType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Menu menu(){return Menu.builder().selectedMenuItem("").menuId(2).build();}

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public List<Category<Product, SubCategoryType>> subCategories(){return new ArrayList<>();}

    @Bean
    public List<Category<String, CategoryType>> getCategories(){return new ArrayList<>();}

    @Bean
    public List<String> getCategoryList(){return  Arrays.asList("Fruit & Vegetable","Snack","Breakfast");}

    @Bean
    public List<String> getSubCategoryList(){return Arrays.asList("Fruit","Vegetable","Chips","Chocolate","Milk","Delicatessen");}
}
