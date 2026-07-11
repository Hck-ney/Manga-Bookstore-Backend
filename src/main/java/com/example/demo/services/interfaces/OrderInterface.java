package com.example.demo.services.interfaces;
import com.example.demo.dto.orderRequest;
import com.example.demo.dto.orderResponse;

import java.util.List;

public interface OrderInterface {
    orderResponse createOrder(orderRequest request);
    List<orderResponse> getOrderList();
    orderResponse updateOrder(Long id, orderRequest request);
}
