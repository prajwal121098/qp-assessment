package com.grocery.booking.service;

import com.grocery.booking.model.GroceryItem;
import com.grocery.booking.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {
    @Autowired
    private GroceryRepository groceryRepository;

    public GroceryItem addGrocery(GroceryItem item) {
        return groceryRepository.save(item);
    }

    public List<GroceryItem> getAllGroceries() {
        return groceryRepository.findAll();
    }

    public GroceryItem updateGrocery(Long id, GroceryItem updatedItem) {
        GroceryItem existingItem = groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        return groceryRepository.save(existingItem);
    }

    public void deleteGrocery(Long id) {
        groceryRepository.deleteById(id);
    }

    public GroceryItem updateInventory(Long id, Integer quantity) {
        GroceryItem item = groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setQuantity(quantity);
        return groceryRepository.save(item);
    }

    public List<GroceryItem> getAllAvailableGroceries() {
        return groceryRepository.findByQuantityGreaterThan(0);
    }
}
