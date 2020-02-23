package com.sunwell.product.repository;

import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sunwell.product.model.Merk;


public interface MerkRepo extends JpaRepository<Merk, Integer>, JpaSpecificationExecutor<Merk> {
	public Merk findByName(String _name);
}
