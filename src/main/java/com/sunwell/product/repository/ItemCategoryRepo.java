package com.sunwell.product.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sunwell.product.model.ItemCategory;


public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Integer>, JpaSpecificationExecutor<ItemCategory> {
	public ItemCategory findByName(String _name);
	public ItemCategory findByCode(String _code);
}
