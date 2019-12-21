package com.treko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.treko.es.model.Customers;
import com.treko.es.repo.CustomersRepo;

@RestController
public class CustomerEsContoller {

	@Autowired
	private CustomersRepo customersRepo;

	@PostMapping("/customers/saveCustomer")
	public int saveCustomer(@RequestBody List<Customers> customers) {
		customersRepo.saveAll(customers);
		return customers.size();
	}
	
	@GetMapping("/customers")
	public Iterable<Customers> findAllCustomers(){
		return customersRepo.findAll();
	}

}
