package com.example.demo.orders.services;

import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.orderResponse;
import com.example.demo.customer.entity.Customer;
import com.example.demo.orders.entity.Orders;
import com.example.demo.enums.status;
import com.example.demo.exceptions.OrderException;
import com.example.demo.orders.mapper.OrderMapper;
import com.example.demo.customer.repository.CustomerRepo;
import com.example.demo.orders.repository.OrderRepo;
import com.example.demo.orders.services.interfaces.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

@Service
public class OrderService implements OrderInterface {

    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    CustomerRepo customerRepo;
    LocalDateTime dateNow = LocalDateTime.now();

    @Override
    public orderResponse createOrder(orderRequest request) {
    if(request.status()==null){
        throw new OrderException("Status value is null", HttpStatus.BAD_REQUEST);
    }

    if (!EnumUtils.isValidEnum(status.class, request.status())) {
        throw new OrderException("Invalid status value: " + request.status(), HttpStatus.BAD_REQUEST);
    }
    Customer cus = customerRepo.findById(request.customer_id()).orElseThrow(()->  new OrderException("Customer_id not found", HttpStatus.NOT_FOUND));
    Orders order = orderMapper.toEntity(request);
    order.setCustomer(cus);
    order.setDate_time(dateNow);
    Orders savedOrder = orderRepo.save(order);
    return orderMapper.toResponse(savedOrder);
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

    @Override
    public void deleteOrder(Long id) {
        Orders order = orderRepo.findById(id).orElseThrow(()-> new OrderException("Cannot find order with this ID",HttpStatus.NOT_FOUND));
        orderRepo.delete(order);
    }
}

