package com.sunwell.product.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.product.model.Metrics;


public interface MetricRepo extends JpaRepository<Metrics, String> {
	public Metrics findByName(String _name);
	public Metrics findDefaultMetric();
}
