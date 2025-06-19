package com.freshsip.orderservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cart_items;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id",nullable = false)
    private Order order;

    private Long itemId;

    @Transient
    private ItemDTO items;

    private double price;
    private int cart_quantity;

    private String item_name;
    private double item_price;

    private LocalDate create_date;
}
