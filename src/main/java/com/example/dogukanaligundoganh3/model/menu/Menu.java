package com.example.dogukanaligundoganh3.model.menu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu {

    private int menuId;
    private String selectedMenuItem;
}
