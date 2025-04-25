package com.example.softwarePatternsCA4.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.softwarePatternsCA4.entity.Cart;
import com.example.softwarePatternsCA4.entity.Customer;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByCustomer(Customer customer);
	Optional<Cart> findByCustomerId(Long customerId);
}
