package com.bil.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Long quantity;
    @Column(unique = true, length = 16)
    private String barcode;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderProduct> orderProducts;
    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;
}
