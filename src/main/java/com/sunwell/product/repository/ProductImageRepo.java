package com.sunwell.product.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.product.model.Product;
import com.sunwell.product.model.ProductImage;



public interface ProductImageRepo extends JpaRepository<ProductImage, Integer> {
	ProductImage findByProduct(Product _p);
	ProductImage findByProductName(String _p);
}
