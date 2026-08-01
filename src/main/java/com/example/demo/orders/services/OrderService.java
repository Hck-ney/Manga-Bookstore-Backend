package com.example.demo.orders.services;

import com.example.demo.enums.Status;
import com.example.demo.inventory.entity.Inventory;
import com.example.demo.inventory.repository.InventoryRepo;
import com.example.demo.orderDetails.dto.OrderDetailsDTO;
import com.example.demo.orderDetails.entity.OrderDetails;
import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.OrderResponse;
import com.example.demo.users.entity.Users;
import com.example.demo.orders.entity.Orders;
import com.example.demo.exceptions.OrderException;
import com.example.demo.orders.mapper.OrderMapper;
import com.example.demo.users.repository.UsersRepository;
import com.example.demo.orders.repository.OrderRepo;
import com.example.demo.orders.services.interfaces.OrderInterface;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.repository.MangaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    UsersRepository usersRepository;
    LocalDateTime dateNow = LocalDateTime.now();

    @Autowired
    MangaRepository mangaRepository;

    @Autowired
    InventoryRepo inventoryRepo;

    @Transactional()
    @Override
    public OrderResponse createOrder(orderRequest request) {
        if (request.status() == null) {
            throw new OrderException("Status value is null", HttpStatus.BAD_REQUEST);
        }
        if (!EnumUtils.isValidEnum(Status.class, request.status())) {
            throw new OrderException("Invalid status value: " + request.status(), HttpStatus.BAD_REQUEST);
        }
        if (request.orderDetailsDTO() == null) {
            throw new OrderException("Order details list should not be null", HttpStatus.BAD_REQUEST);
        }
        Users cus = usersRepository.findById(request.customer_id()).orElseThrow(() -> new OrderException("Customer_id not found", HttpStatus.NOT_FOUND));
        Orders orders = orderMapper.toEntity(request);
        orders.setCustomer_name(cus.getUsername());
        orders.setEmail(cus.getEmail());
        orders.setAddress(cus.getAddress());
        orders.setUsers(cus);
        orders.setDate_time(dateNow);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        // to get Total amount of all order details
        BigDecimal sum = BigDecimal.ZERO;

        for (OrderDetailsDTO od : request.orderDetailsDTO()) {
            OrderDetails orderDet = new OrderDetails();
            Manga manga = mangaRepository.findById(od.product_id()).orElseThrow(() -> new OrderException("Product associated with this ID cannot be found", HttpStatus.NOT_FOUND));
            orderDet.setManga(manga);
            orderDet.setPrice(manga.getPrice());
            orderDet.setQuantity(od.quantity());
            orderDet.setTotal(orderDet.getPrice().multiply(BigDecimal.valueOf(orderDet.getQuantity())));
            orderDet.setOrder(orders);
            orderDetailsList.add(orderDet);
            Inventory inv = inventoryRepo.findByMangaId(manga.getId());
            if(inv.getStockQuantity()-od.quantity()<0){
                throw new OrderException("Stock quantity of " + inv.getManga().getTitle() + " isn't enough", HttpStatus.CONFLICT);
            }
            inv.setStockQuantity(inv.getStockQuantity()-od.quantity());
            inventoryRepo.save(inv);
            sum = sum.add(orderDet.getTotal());
        }
        orders.setTotal(sum);
        orders.setOrderDetails(orderDetailsList);
        orderRepo.save(orders);
        return OrderResponse.from(orders);
    }


    @Override
    public List<OrderResponse> getOrderList() {
        List <Orders> order = orderRepo.findAll();
        List <OrderResponse> responses = new ArrayList<>();

        for(Orders orders: order)
        {
            responses.add(OrderResponse.from(orders));
        }
        return responses;
    }

    // Change status of an order
//    @Override
//    public orderResponse updateOrder(Long id, orderRequest request) {
//        // 1. Find the existing order
//        Orders existingOrder = orderRepo.findById(id)
//                .orElseThrow(() -> new OrderException("No order found with this ID", HttpStatus.NOT_FOUND));
//
//        if (request.status() != null && EnumUtils.isValidEnum(status.class, request.status())) {
//            existingOrder.setStatus(status.valueOf(request.status()));
//        }
//        Orders savedOrder = orderRepo.save(existingOrder);
//        return new orderResponse(
//                savedOrder.getId(),
//                savedOrder.getCustomer(),
//                savedOrder.getStatus(),
//                savedOrder.getDate_time()
//        );
//    }

    @Override
    public void deleteOrder(Long order_id) {
        Orders order = orderRepo.findById(order_id).orElseThrow(()-> new OrderException("Cannot find order with this ID",HttpStatus.NOT_FOUND));
        orderRepo.delete(order);
    }

}