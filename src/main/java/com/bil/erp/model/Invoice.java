package com.bil.erp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;

    private Instant date;

    @OneToMany(mappedBy = "invoice")
    private List<Order> order;

    @OneToMany(mappedBy = "invoice")
    private List<Payment> payment;
}
