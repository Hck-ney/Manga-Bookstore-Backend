package com.example.demo.mapper;

import com.example.demo.dto.orderRequest;
import com.example.demo.dto.orderResponse;
import com.example.demo.entity.Orders;
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