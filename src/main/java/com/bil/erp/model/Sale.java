package com.bil.erp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    private Date saleDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @ManyToOne
    private Order order;
}
