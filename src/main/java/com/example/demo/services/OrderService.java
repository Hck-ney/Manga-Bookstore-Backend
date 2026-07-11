package com.example.demo.services;

import com.example.demo.dto.orderRequest;
import com.example.demo.dto.orderResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Orders;
import com.example.demo.enums.status;
import com.example.demo.exceptions.OrderException;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.OrderRepo;
import com.example.demo.services.interfaces.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

@Service
public class OrderService implements OrderInterface {
    @Autowired
    OrderRepo orderRepo;

    // Used to get the Customer object by referencing its ID
    @Autowired
    CustomerRepo customerRepo;

    LocalDateTime dateNow = LocalDateTime.now();
    @Override
    public orderResponse createOrder(orderRequest request) {
    Customer cus = customerRepo.getReferenceById(request.customer_id());

    Orders order = new Orders();
    order.setStatus(status.valueOf(request.status()));
    order.setDate_time(dateNow);
    order.setCustomer(cus);

    Orders savedOrder = orderRepo.save(order);
    return new orderResponse(
            order.getId(),
            order.getCustomer(),
            order.getStatus(),
            order.getDate_time()
    );
}

    @Override
    public List<orderResponse> getOrderList() {
        List <Orders> order = orderRepo.findAll();
        List <orderResponse> responses = new ArrayList<>();

        for(Orders orders: order){
            responses.add(new orderResponse(
                    orders.getId(),
                    orders.getCustomer(),
                    orders.getStatus(),
                    orders.getDate_time()
            ));
        }
        return responses;
    }

    // Change status of an order
    @Override
    public orderResponse updateOrder(Long id, orderRequest request) {
        // 1. Find the existing order
        Orders existingOrder = orderRepo.findById(id)
                .orElseThrow(() -> new OrderException("No order found with this ID", HttpStatus.NOT_FOUND));

        if (request.status() != null && EnumUtils.isValidEnum(status.class, request.status())) {
            existingOrder.setStatus(status.valueOf(request.status()));
        }
        Orders savedOrder = orderRepo.save(existingOrder);
        return new orderResponse(
                savedOrder.getId(),
                savedOrder.getCustomer(),
                savedOrder.getStatus(),
                savedOrder.getDate_time()
        );
    }
}

