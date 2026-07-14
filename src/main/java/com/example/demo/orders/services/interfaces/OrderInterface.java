package com.example.demo.orders.services.interfaces;
import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.orderResponse;

import java.util.List;

public interface OrderInterface {
    orderResponse createOrder(orderRequest request);
    List<orderResponse> getOrderList();
    orderResponse updateOrder(Long id, orderRequest request);
    void deleteOrder(Long id);
}
