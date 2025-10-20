package com.rodrigo134.products_api.dto;

import java.math.BigDecimal;

public record RequestDto(
                         String productName,
                         String productDescription,
                         BigDecimal productPrice) {
}
