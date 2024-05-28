package com.bil.erp.model;

import com.bil.erp.model.enums.PaymentModelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Invoice invoice;

    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private PaymentModelEnum model;
}
