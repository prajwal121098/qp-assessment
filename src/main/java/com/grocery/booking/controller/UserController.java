package com.grocery.booking.controller;

import com.grocery.booking.model.GroceryItem;
import com.grocery.booking.model.Order;
import com.grocery.booking.service.GroceryService;
import com.grocery.booking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/groceries")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private GroceryService groceryService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAvailableGroceries() {
        return ResponseEntity.ok(groceryService.getAllAvailableGroceries());
    }

    @PostMapping("/order")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }
}

