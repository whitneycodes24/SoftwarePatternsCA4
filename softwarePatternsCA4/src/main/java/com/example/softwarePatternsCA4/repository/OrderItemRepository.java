package com.example.softwarePatternsCA4.repository;


import com.example.softwarePatternsCA4.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
