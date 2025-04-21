package com.example.softwarePatternsCA4.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	 @PostMapping("/register")
	    public Customer registerCustomer(@RequestBody Customer customer) {
	        return customerService.registerCustomer(customer);
	    }

	    @GetMapping
	    public List<Customer> getAllCustomers() {
	        return customerService.getAllCustomers();
	    }

	    @GetMapping("/{id}")
	    public Optional<Customer> getCustomerById(@PathVariable Long id) {
	        return customerService.getCustomerById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteCustomer(@PathVariable Long id) {
	        customerService.deleteCustomer(id);
	    }
}
