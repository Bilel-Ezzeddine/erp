package com.bil.erp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterCriteria {
    private String name;
    private String price;
    private String quantity;
    private String barcode;
    private String categoryName;
}
