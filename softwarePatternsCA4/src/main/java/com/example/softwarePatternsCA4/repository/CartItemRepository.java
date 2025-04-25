package com.example.softwarePatternsCA4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.softwarePatternsCA4.entity.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
