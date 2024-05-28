package com.bil.erp.dto.order;

import com.bil.erp.dto.client.ClientResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Instant orderDate;
    private Double orderTotal;
    private ClientResponse client;
    private List<OrderProductResponse> products;
}
