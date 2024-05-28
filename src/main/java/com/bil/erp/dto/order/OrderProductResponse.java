package com.bil.erp.dto.order;

import com.bil.erp.dto.product.ProductResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductResponse {
    private Long id;
    private ProductResponse product;
    private Long quantity;
    private Double salePrice;
}
