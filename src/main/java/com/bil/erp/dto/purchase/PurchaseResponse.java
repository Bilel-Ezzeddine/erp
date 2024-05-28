package com.bil.erp.dto.purchase;

import com.bil.erp.dto.product.ProductResponse;
import com.bil.erp.dto.supplier.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseResponse {
    private Long id;
    private ProductResponse product;
    private SupplierResponse supplier;
    private Long quantity;
    private Date purchaseDate;
    private Double price;
}
