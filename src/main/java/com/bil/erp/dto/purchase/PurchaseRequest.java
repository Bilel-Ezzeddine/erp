package com.bil.erp.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequest {
    private Long productId;
    private Long supplierId;
    private Long quantity;
    private Date purchaseDate;
    private Double price;
}
