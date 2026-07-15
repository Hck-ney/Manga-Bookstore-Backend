package com.example.demo.orderDetails.controller;

import com.example.demo.orderDetails.dto.OrderDetailsDTO;
import com.example.demo.orderDetails.entity.OrderDetails;
import com.example.demo.orderDetails.services.OrderDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailsController {
    @Autowired
    OrderDetailsServices orderDetailsServices;

    @PostMapping("/order_details")
    public ResponseEntity<OrderDetails> createOrderDetail(@RequestBody OrderDetailsDTO orderDetailsDTO){
        return  new ResponseEntity<>(orderDetailsServices.createOrderDetail(orderDetailsDTO), HttpStatus.CREATED);
    }
    @GetMapping("/order_details/{orderDetailId}")
    public OrderDetails getOrderDetail(@PathVariable Long orderDetailId){
        return orderDetailsServices.getOrderDetail(orderDetailId);
    }
    @GetMapping("/order_details")
    public List<OrderDetails> listOrderDetail(){
        return orderDetailsServices.listOrderDetail();
    }
    @PutMapping("/OrderDetails/{orderDetailId}")
    public OrderDetails updateOrderDetail(@PathVariable Long orderDetailId, @RequestBody OrderDetailsDTO orderDetailsDTO){
        return orderDetailsServices.updateOrderDetail(orderDetailId, orderDetailsDTO);
    }
    @DeleteMapping("/Order_details/{orderDetailId}")
    public void deleteOrderDetail(@PathVariable Long orderDetailId){
        orderDetailsServices.deleteOrderDetail(orderDetailId);
    }
}
