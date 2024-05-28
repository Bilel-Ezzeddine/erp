package com.bil.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String phone;
    private String gsmPhone;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    @OneToMany(mappedBy = "supplier")
    private List<Purchase> purchases;

}
