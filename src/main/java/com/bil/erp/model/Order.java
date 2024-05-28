package com.bil.erp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Instant orderDate;
    private Double orderTotal = 0D;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    @ManyToOne
    private Invoice invoice;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
}
