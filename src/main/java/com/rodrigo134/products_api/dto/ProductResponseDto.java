package com.rodrigo134.products_api.dto;

import java.math.BigDecimal;

public class ProductResponseDto {

    private String name;
    private String description;
    private BigDecimal price;

    // Construtor padrão
    public ProductResponseDto() {}

    // Construtor completo
    public ProductResponseDto( String name, String description, BigDecimal price) {

        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters e setters


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }


}
