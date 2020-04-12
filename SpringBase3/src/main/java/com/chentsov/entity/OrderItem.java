package com.chentsov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_items")
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    @Column
    private BigDecimal itemPrice;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", itemPrice=" + itemPrice +
                ", product=" + product +
                '}';
    }
}