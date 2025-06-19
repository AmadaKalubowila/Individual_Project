package com.freshsip.orderservice;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderItemsDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cart_items;
    private Long cart_id;
    private List<ProductDTO> selectedProducts;
    private String item_name;
    private double item_price;
    private LocalDate create_date;

}
