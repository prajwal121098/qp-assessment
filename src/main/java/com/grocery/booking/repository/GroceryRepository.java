package com.grocery.booking.repository;

import com.grocery.booking.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findByQuantityGreaterThan(int quantity);
}

