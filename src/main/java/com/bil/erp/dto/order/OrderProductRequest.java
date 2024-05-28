package com.bil.erp.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductRequest {
    private Long productId;
    private Long quantity;
    private Double salePrice;
}
