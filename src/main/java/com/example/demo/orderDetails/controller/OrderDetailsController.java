package com.example.demo.orderDetails.controller;

import com.example.demo.orderDetails.dto.OrderDetailsDTO;
import com.example.demo.orderDetails.entity.OrderDetails;
import com.example.demo.orderDetails.services.OrderDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailsController {
    @Autowired
    OrderDetailsServices orderDetailsServices;

    @PostMapping("/OrderDetails/")
    public OrderDetails createOrderDetail(@RequestBody OrderDetailsDTO orderDetailsDTO){
        return  orderDetailsServices.createOrderDetail(orderDetailsDTO);
    }
    @GetMapping("/OrderDetails/{orderDetailId}")
    public OrderDetails getOrderDetail(@PathVariable Long orderDetailId){
        return orderDetailsServices.getOrderDetail(orderDetailId);
    }
    @GetMapping("/OrderDetails/")
    public List<OrderDetails> listOrderDetail(){
        return orderDetailsServices.listOrderDetail();
    }
    @PutMapping("/OrderDetails/{orderDetailId}")
    public OrderDetails updateOrderDetail(@PathVariable Long orderDetailId, @RequestBody OrderDetailsDTO orderDetailsDTO){
        return orderDetailsServices.updateOrderDetail(orderDetailId, orderDetailsDTO);
    }
    @DeleteMapping("/OrderDetails/{orderDetailId}")
    public void deleteOrderDetail(@PathVariable Long orderDetailId){
        orderDetailsServices.deleteOrderDetail(orderDetailId);
    }
}
