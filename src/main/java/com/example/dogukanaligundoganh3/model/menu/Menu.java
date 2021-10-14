package com.example.dogukanaligundoganh3.model.menu;

import com.example.dogukanaligundoganh3.type.CategoryType;
import com.example.dogukanaligundoganh3.type.SubCategoryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu {

    private int menuId;
    private String selectedMenuItem;
    private CategoryType categoryType;
    private SubCategoryType subCategoryType;
    private String productName;
    private String searchLetters;
}
