package com.example.demo.orders.services.interfaces;
import com.example.demo.orders.dto.OrderResponse;
import com.example.demo.orders.dto.orderRequest;

import java.util.List;

public interface OrderInterface {
    OrderResponse createOrder(orderRequest request);
     List<OrderResponse> getOrderList();
    // orderResponse updateOrder(Long id, orderRequest request);
     void deleteOrder(Long id);
}
