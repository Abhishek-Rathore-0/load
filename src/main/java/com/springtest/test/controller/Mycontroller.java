package com.springtest.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.test.entity.Load;
import com.springtest.test.repository.loadRepository;
import com.springtest.test.exception.ResourceNotFoundException;

@RestController
public class Mycontroller {
	
	@Autowired
	private loadRepository loadrepository;
	
//	@GetMapping("/load")
//	public List<Load> getAllLoad(){
//		return this.loadrepository.findAll();
//	}
		
	//Question 1
	@PostMapping("/load")
	public Load insertL(@RequestBody Load load) {		return this.loadrepository.save(load);
	}
	//Question 2
	@GetMapping("/load")
	public List<Load> getFoos(@RequestParam String shipperId) {
		return	this.loadrepository.findByshipperId(shipperId);
	}
	
	//Question 3
	@GetMapping("/load/{loadld}")
	public ResponseEntity<Load> getLoadById(@PathVariable(value="loadld") int loadId) throws ResourceNotFoundException{
		Load load=loadrepository.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("Load not found."));
		return ResponseEntity.ok().body(load);
	}
	
	//Question 4
	@PutMapping("/load/{loadld}")
	public ResponseEntity<Load> updateLoadById(@PathVariable(value="loadld") int loadId, @Validated @RequestBody Load loaddetails) throws ResourceNotFoundException{
		Load load=loadrepository.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("Load not found."));
		load.setLoadingPoint(loaddetails.getLoadingPoint());
		load.setUnloadingPoint(loaddetails.getUnloadingPoint());
		load.setDate(loaddetails.getDate());
		load.setNoOfTrucks(loaddetails.getNoOfTrucks());
		load.setProductType(loaddetails.getProductType());
		load.setShipperId(loaddetails.getShipperId());
		load.setTruckType(loaddetails.getTruckType());
		load.setWeight(loaddetails.getWeight());
		load.setComment(loaddetails.getComment());
		return ResponseEntity.ok().body(load);
	}
		
	//Question 5
	@DeleteMapping("/load/{loadld}")
	public Map<String, Boolean> deleteLoad(@PathVariable(value="loadld") int loadId) throws ResourceNotFoundException{
		Load load = loadrepository.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("Load not found."));	 
		this.loadrepository.delete(load);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return response;
	}
		
}
