package com.example.demo.orders.controller;

import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.orderResponse;
import com.example.demo.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService serv;

    // Creates Orders
    @PostMapping("/orders")
    public ResponseEntity<orderResponse> createOrder(@RequestBody orderRequest request){
        return new ResponseEntity<>(serv.createOrder(request), HttpStatus.CREATED);
    }

    // List all orders
    @GetMapping("/orders")
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
