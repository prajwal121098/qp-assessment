package com.grocery.booking.controller;

import com.grocery.booking.model.GroceryItem;
import com.grocery.booking.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/groceries")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private GroceryService groceryService;

    @PostMapping
    public ResponseEntity<GroceryItem> addGrocery(@RequestBody GroceryItem item) {
        return ResponseEntity.ok(groceryService.addGrocery(item));
    }

    @GetMapping
    public ResponseEntity<List<GroceryItem>> viewGroceries() {
        return ResponseEntity.ok(groceryService.getAllGroceries());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGrocery(@PathVariable Long id, @RequestBody GroceryItem item) {
        return ResponseEntity.ok(groceryService.updateGrocery(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrocery(@PathVariable Long id) {
        groceryService.deleteGrocery(id);
        return ResponseEntity.ok("Grocery deleted successfully");
    }

    @PatchMapping("/{id}/inventory")
    public ResponseEntity<GroceryItem> updateInventory(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(groceryService.updateInventory(id, quantity));
    }
}
