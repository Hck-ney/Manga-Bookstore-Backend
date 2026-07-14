package com.example.demo.orders.controller;

import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.orderResponse;
import com.example.demo.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {

    LocalDateTime dateNow = LocalDateTime.now();

    @Autowired
    OrderService serv;

    // Creates Orders
    @PostMapping("/orders/")
    public orderResponse createOrder(@RequestBody orderRequest request){
        return serv.createOrder(request);
    }

    // List all orders
    @GetMapping("/orders/")
    public List<orderResponse> getOrderList(){
        return serv.getOrderList();
    }

    // Update specific order
    @PutMapping("/orders/{order_id}")
    public orderResponse updateOrder(@PathVariable Long id, @RequestBody orderRequest request){
        return serv.updateOrder(id, request);
    }
    @DeleteMapping("/orders/{order_id}")
    public void deleteOrder(@PathVariable Long id){
         serv.deleteOrder(id);
    }
}
