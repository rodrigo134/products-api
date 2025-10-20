package com.rodrigo134.products_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo134.products_api.dto.ProductResponseDto;
import com.rodrigo134.products_api.dto.RequestDto;
import com.rodrigo134.products_api.model.ProductModel;
import com.rodrigo134.products_api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateProduct() throws Exception {
        RequestDto requestDto = new RequestDto("Produto A", "Descrição A", new BigDecimal("10.0"));
        ProductResponseDto responseDto = new ProductResponseDto("Produto A", "Descrição A", new BigDecimal("10.0"));

        when(productService.createProduct(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Produto A"))
                .andExpect(jsonPath("$.description").value("Descrição A"))
                .andExpect(jsonPath("$.price").value(10.0));

        verify(productService, times(1)).createProduct(requestDto);
    }

    @Test
    void testGetAllProducts() throws Exception {
        ProductResponseDto productA = new ProductResponseDto("Produto A", "Descrição A", new BigDecimal("10.0"));
        ProductResponseDto productB = new ProductResponseDto("Produto B", "Descrição B", new BigDecimal("20.0"));

        List<ProductResponseDto> products = Arrays.asList(productA, productB);

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Produto A"))
                .andExpect(jsonPath("$[1].name").value("Produto B"))
                .andExpect(jsonPath("$[0].description").value("Descrição A"))
                .andExpect(jsonPath("$[1].description").value("Descrição B"))
                .andExpect(jsonPath("$[0].price").value(10.0))
                .andExpect(jsonPath("$[1].price").value(20.0));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testGetProductById() throws Exception {
        ProductResponseDto product = new ProductResponseDto("Produto A", "Descrição A", new BigDecimal("10.0"));

        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Produto A"))
                .andExpect(jsonPath("$.description").value("Descrição A"))
                .andExpect(jsonPath("$.price").value(10.0));

        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduct() throws Exception {
        RequestDto requestDto = new RequestDto("Produto A Atualizado", "Descrição Atualizada", new BigDecimal("15.0"));
        ProductResponseDto responseDto = new ProductResponseDto("Produto A Atualizado", "Descrição Atualizada", new BigDecimal("15.0"));

        when(productService.updateProduct(1L, requestDto)).thenReturn(responseDto);

        mockMvc.perform(put("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Produto A Atualizado"))
                .andExpect(jsonPath("$.description").value("Descrição Atualizada"))
                .andExpect(jsonPath("$.price").value(15.0));

        verify(productService, times(1)).updateProduct(1L, requestDto);
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/produtos/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1L);
    }
}
