package com.example.demo.orders.controller;

import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.OrderResponse;
import com.example.demo.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService serv;

    // Creates Orders
    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody orderRequest request){
        // return new ResponseEntity<>(serv.createOrder(request), HttpStatus.CREATED);
        return serv.createOrder(request);
    }

    // List all orders
    @GetMapping("/orders")
    public List<OrderResponse> getOrderList(){
        return serv.getOrderList();
    }

    // Update specific order
//    @PutMapping("/orders/{order_id}")
//    public OrderResponse updateOrder(@PathVariable Long id, @RequestBody orderRequest request){
//        return serv.updateOrder(id, request);
//    }
    @DeleteMapping("/orders/{order_id}")
    public void deleteOrder(@PathVariable Long order_id){
         serv.deleteOrder(order_id);
    }
}
