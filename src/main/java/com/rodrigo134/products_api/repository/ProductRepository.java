package com.rodrigo134.products_api.repository;

import com.rodrigo134.products_api.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<ProductModel,Long > {
}
