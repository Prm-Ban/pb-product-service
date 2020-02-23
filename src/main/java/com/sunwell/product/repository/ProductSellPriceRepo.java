package com.sunwell.product.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.product.model.ProductSellPrice;
import com.sunwell.product.model.ProductSellPricePK;



public interface ProductSellPriceRepo extends JpaRepository<ProductSellPrice, ProductSellPricePK> {
	
}
