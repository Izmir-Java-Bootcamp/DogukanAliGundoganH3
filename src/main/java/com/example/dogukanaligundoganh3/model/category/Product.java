package com.example.dogukanaligundoganh3.model.category;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String name;
    private String description;
    private int price;


}
