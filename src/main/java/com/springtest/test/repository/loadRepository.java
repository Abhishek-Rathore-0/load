package com.springtest.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springtest.test.entity.Load;

@Repository
public interface loadRepository extends JpaRepository<Load, Integer>{
	
	@Query(value = "select u.* from loadtable u where u.shipper_id=?1 ;", nativeQuery = true)
	List<Load> findByshipperId(String shipper_id);
}
