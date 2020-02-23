package com.sunwell.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.product.model.SellPriceLevel;


public interface SellPriceLevelRepo extends JpaRepository<SellPriceLevel, Integer> {
	SellPriceLevel findByName(String _name);
}
