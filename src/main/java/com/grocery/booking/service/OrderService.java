package com.grocery.booking.service;

import com.grocery.booking.model.GroceryItem;
import com.grocery.booking.model.Order;
import com.grocery.booking.model.OrderItem;
import com.grocery.booking.repository.GroceryRepository;
import com.grocery.booking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryRepository groceryRepository;

    @Transactional
    public Order placeOrder(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            GroceryItem item = groceryRepository.findById(orderItem.getGroceryItemId())
                    .orElseThrow(() -> new RuntimeException("Grocery item not found"));
            if (item.getQuantity() < orderItem.getQuantity()) {
                throw new RuntimeException("Insufficient inventory for item: " + item.getName());
            }
            item.setQuantity(item.getQuantity() - orderItem.getQuantity());
            groceryRepository.save(item);
        }
        return orderRepository.save(order);
    }
}

