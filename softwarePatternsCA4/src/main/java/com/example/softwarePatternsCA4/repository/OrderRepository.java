package com.example.softwarePatternsCA4.repository;


import com.example.softwarePatternsCA4.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}
