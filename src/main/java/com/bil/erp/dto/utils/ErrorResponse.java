package com.bil.erp.dto.utils;

import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
public class ErrorResponse {
    private final String messageId;
    private final String details;
}
