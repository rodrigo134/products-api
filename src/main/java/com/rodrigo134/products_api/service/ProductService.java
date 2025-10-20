package com.rodrigo134.products_api.service;


import com.rodrigo134.products_api.dto.ProductResponseDto;
import com.rodrigo134.products_api.dto.RequestDto;
import com.rodrigo134.products_api.exception.ProductNotFoundException;
import com.rodrigo134.products_api.model.ProductModel;
import com.rodrigo134.products_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public ProductResponseDto createProduct(RequestDto requestDto) {

        ProductModel productModel = new ProductModel();
        //conversão dto -> model

        productModel.setProductName(requestDto.productName());
        productModel.setProductDescription(requestDto.productDescription());
        productModel.setProductPrice(requestDto.productPrice());

        //salva model no banco
        productRepository.save(productModel);

        //Converte Model -> Response DTO
        ProductResponseDto response = new ProductResponseDto();

        response.setName(productModel.getProductName());
        response.setDescription(productModel.getProductDescription());
        response.setPrice(productModel.getProductPrice());

        return  response;
    }


    public List<ProductResponseDto> findAll() {
        List<ProductModel> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDto(
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getProductPrice()
                ))
                .toList();
    }

    public ProductResponseDto findById(Long id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não existe"));
        return new ProductResponseDto(
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice()
        );
    }

    public ProductResponseDto updateProduct(Long id ,RequestDto requestDto) {
        ProductModel existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Produto não encontrado com id: " + id));

        //atualiza os campos do produto existente com os valores do requestDto
         existingProduct.setProductName(requestDto.productName());
         existingProduct.setProductDescription(requestDto.productDescription());
         existingProduct.setProductPrice(requestDto.productPrice());

        //salva model no banco
        productRepository.save(existingProduct);

        //conversão dto -> model
        ProductResponseDto response = new ProductResponseDto();
        response.setName(existingProduct.getProductName());
        response.setDescription(existingProduct.getProductDescription());
        response.setPrice(existingProduct.getProductPrice());


        return  response;
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Produto não encontrado com id: " + id);
        }
        productRepository.deleteById(id);

    }

}
