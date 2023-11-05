package com.tobeto.spring_1b;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    private int id;
    private String productName;
    private String brandName;
    private int unitPrice;
    private int stock;
    private String color;
    private String size;
}
