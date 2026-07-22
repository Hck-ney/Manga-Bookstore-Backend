package com.example.demo.orderDetails.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.orderDetails.dto.OrderDetailsDTO;
import com.example.demo.orderDetails.entity.OrderDetails;
import com.example.demo.manga.entity.Manga;
import com.example.demo.orderDetails.repository.OrderDetailsRepo;
import com.example.demo.orders.entity.Orders;
import com.example.demo.orders.repository.OrderRepo;
import com.example.demo.manga.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderDetailsServices {
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private MangaRepository mangaRepository;

    public OrderDetails createOrderDetail(OrderDetailsDTO orderDetailsDTO){
        Orders orders = orderRepo.findById(orderDetailsDTO.order_id()).orElseThrow(()-> new OrderException("Invalid Order id", HttpStatus.NOT_FOUND));
        Manga manga = mangaRepository.findById(orderDetailsDTO.product_id()).orElseThrow(()-> new OrderException("Invalid Product ID", HttpStatus.NOT_FOUND));

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(orders);
        orderDetails.setQuantity(orderDetailsDTO.quantity());
        orderDetails.setManga(manga);
        orderDetails.setPrice(manga.getPrice());
        orderDetails.setTotal(orderDetails.getPrice().multiply(BigDecimal.valueOf(orderDetailsDTO.quantity())));
        return orderDetailsRepo.save(orderDetails);
    }

    public OrderDetails getOrderDetail(Long orderDetailId){
        return orderDetailsRepo.findById(orderDetailId).orElseThrow(()-> new OrderException("orderDetailId not found", HttpStatus.NOT_FOUND));
    }

    public List<OrderDetails> listOrderDetail(){
        return orderDetailsRepo.findAll();
    }

    // may update product id and quantity
    public OrderDetails updateOrderDetail(Long orderDetailId, OrderDetailsDTO orderDetailsDTO){
        OrderDetails orderDetails = orderDetailsRepo.findById(orderDetailId).orElseThrow(()-> new OrderException("OrderDetail associated with this Id is not found", HttpStatus.NOT_FOUND));
        if (orderDetailsDTO.product_id() != null) {
            Manga manga = mangaRepository.findById(orderDetailsDTO.product_id()).orElseThrow(()-> new OrderException("Product id is invalid", HttpStatus.NOT_FOUND));
            orderDetails.setManga(manga);
        }
        orderDetails.setQuantity(orderDetailsDTO.quantity());
        return orderDetailsRepo.save(orderDetails);
    }

    public void deleteOrderDetail(Long orderDetailId){
        OrderDetails order = orderDetailsRepo.findById(orderDetailId).orElseThrow(()-> new OrderException("OrderDetail associated with this Id is not found",HttpStatus.NOT_FOUND));
        orderDetailsRepo.delete(order);
    }
}
