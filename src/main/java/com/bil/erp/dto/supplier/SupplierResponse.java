package com.bil.erp.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierResponse {
    private Long id;
    private String email;
    private String phone;
    private String gsmPhone;
    private String address;
    private String city;
    private String zipCode;
    private String country;
}
