package com.finops.spotprice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finops.spotprice.model.SpotPrices;

@Repository
public interface SpotRepository extends JpaRepository<SpotPrices, Long> {

	// Query SQL
	@Query(value = "select * from spotprices where cloud_name = ? and instance_type = ? and region = ? and product_description = ?", nativeQuery = true)
	SpotPrices findBySelectUsingcloudNameAndinstanceTypeAndregionAndProductDescription(String cloudName,
			String instanceType, String region, String ProductDescription);

	// API REST
	Page<SpotPrices> findBycloudName(String cloudName, Pageable pageable);
	
	Page<SpotPrices> findByregion(String region, Pageable pageable);

	Page<SpotPrices> findByinstanceType(String instanceType, Pageable pageable);
	
	Page<SpotPrices> findBycloudNameAndRegion(String cloudName, String region, Pageable pageable);
	
	Page<SpotPrices> findBycloudNameAndInstanceType(String cloudName, String instanceType, Pageable pageable);
	
	Page<SpotPrices> findBycloudNameAndRegionAndInstanceType(String cloudName, String region, String instanceType, Pageable pageable);
	
	// Thymeleaf 
	List<SpotPrices> findBycloudName(String cloudName);
	
	List<SpotPrices> findByregion(String region);

	List<SpotPrices> findByinstanceType(String instanceType);
	
	List<SpotPrices> findBycloudNameAndRegion(String cloudName, String region);
	
	List<SpotPrices> findBycloudNameAndInstanceType(String cloudName, String instanceType);
	
	List<SpotPrices> findBycloudNameAndRegionAndInstanceType(String cloudName, String region, String instanceType);
}
