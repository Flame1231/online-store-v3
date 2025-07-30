package ru.otus.notificationmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private long id;
    private long order_id;
    private long product_id;
    private int quantity;
    @NonNull
    private BigDecimal price;
    @NonNull
    private BigDecimal sale;
    @NonNull
    private BigDecimal total_price;

    private long user_id;
}
