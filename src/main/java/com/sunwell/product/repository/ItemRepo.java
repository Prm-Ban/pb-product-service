package com.sunwell.product.repository;


import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sunwell.product.model.Item;
import com.sunwell.product.model.ItemCategory;


public interface ItemRepo extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
	public Item findByName(String _name);
	public Page<Item> findByCategoriesIn(ItemCategory _ic, Pageable _page);
	public Page<Item> findByCategories_SystemIdIn(Integer _id, Pageable _page);
}
