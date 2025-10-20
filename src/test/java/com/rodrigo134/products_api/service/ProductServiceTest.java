package com.rodrigo134.products_api.service;

import com.rodrigo134.products_api.dto.ProductResponseDto;
import com.rodrigo134.products_api.dto.RequestDto;
import com.rodrigo134.products_api.exception.ProductNotFoundException;
import com.rodrigo134.products_api.model.ProductModel;
import com.rodrigo134.products_api.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void deveCriarProdutoComSucesso() {
        RequestDto request = new RequestDto("Notebook", "Dell XPS", BigDecimal.valueOf(5500.0));
        ProductModel model = new ProductModel("Notebook", "Dell XPS", BigDecimal.valueOf(5500.0));
        model.setProductId(1L);

        when(productRepository.save(any(ProductModel.class))).thenReturn(model);

        ProductResponseDto response = productService.createProduct(request);

        assertNotNull(response);
        assertEquals("Notebook", response.getName());
        assertEquals(BigDecimal.valueOf(5500.0), response.getPrice());
        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Long id = 1L;
        RequestDto request = new RequestDto("Mouse", "Logitech MX", BigDecimal.valueOf(250.0));
        ProductModel existing = new ProductModel("Antigo", "Desc", BigDecimal.valueOf(100.0));
        existing.setProductId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(existing));
        when(productRepository.save(existing)).thenReturn(existing);

        ProductResponseDto response = productService.updateProduct(id, request);

        assertEquals("Mouse", response.getName());
        assertEquals(BigDecimal.valueOf(250.0), response.getPrice());
        verify(productRepository, times(1)).save(existing);
    }

    @Test
    void deveLancarExcecaoAoAtualizarProdutoInexistente() {
        Long id = 99L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(id, new RequestDto("X", "Y", BigDecimal.ONE)));
    }

    @Test
    void deveDeletarProdutoExistente() {
        Long id = 1L;

        when(productRepository.existsById(id)).thenReturn(true);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).existsById(id);
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoDeletarProdutoInexistente() {
        Long id = 99L;

        when(productRepository.existsById(id)).thenReturn(false);

        assertThrows(ProductNotFoundException.class,
                () -> productService.deleteProduct(id));

        verify(productRepository, times(1)).existsById(id);
        verify(productRepository, never()).deleteById(any());
    }
}
