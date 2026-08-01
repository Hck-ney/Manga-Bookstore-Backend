package com.example.demo.orders.mapper;

import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.OrderResponse;
import com.example.demo.orders.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    // ignored values will be on the service layer

    OrderResponse toResponse(Orders order);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "date_time", ignore = true)
    Orders toEntity(orderRequest request);

}