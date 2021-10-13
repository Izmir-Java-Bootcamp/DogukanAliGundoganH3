package com.example.dogukanaligundoganh3.model.category;

import com.example.dogukanaligundoganh3.model.interfaces.Type;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class Category<T,S> implements Type<S> {

    private String name;
    private List<T> elements;
    private S type;

    public Category(String name,S type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public S getType() {
        return type;
    }

    public void addElement(T element){
        elements.add(element);
    }

}
