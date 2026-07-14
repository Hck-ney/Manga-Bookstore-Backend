package com.example.demo.orders.mapper;

import com.example.demo.orders.dto.orderRequest;
import com.example.demo.orders.dto.orderResponse;
import com.example.demo.orders.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    // ignored values will be on the service layer

    orderResponse toResponse(Orders order);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "date_time", ignore = true)
    Orders toEntity(orderRequest request);

}