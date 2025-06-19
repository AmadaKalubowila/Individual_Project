package com.freshsip.productservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class ProductDTO {
    private Long id;
    private double price;
    private int quantity;
    private String item_name;
    private double item_price;
}

