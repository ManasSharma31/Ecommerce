package com.manas.order_service.Mapper;

import com.manas.order_service.Entity.Order;
import com.manas.order_service.Entity.OrderLine;
import com.manas.order_service.Model.OrderLineRequest;
import com.manas.order_service.Model.OrderLineResponse;

public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
        .id(orderLineRequest.id())
        .order(Order.builder().id(orderLineRequest.orderId()).build())
        .productId(orderLineRequest.productId())
        .quantity(orderLineRequest.quantity())
        .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
    return new OrderLineResponse(orderLine.getId(),orderLine.getProductId(),orderLine.getQuantity());
}
}
