package com.rodrigo134.products_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestDto(
        @NotBlank(message = "Nome do produto é necessário") String productName,
                         String productDescription,
        @NotNull(message = "Preço do produto é necessário")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
                         BigDecimal productPrice) {
}
