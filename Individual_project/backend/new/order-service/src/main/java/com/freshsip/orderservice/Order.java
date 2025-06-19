package com.freshsip.orderservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cart_id;
    private LocalDate create_date;
    private LocalTime create_time;
    private double full_total;
    private int status;

    private Long userId;
    private String u_email;

    @Transient
    private UserDTO user;
}
