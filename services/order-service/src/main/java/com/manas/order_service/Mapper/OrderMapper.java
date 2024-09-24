package com.manas.order_service.Mapper;

import org.hibernate.metamodel.mapping.ordering.ast.OrderingExpression;
import org.springframework.stereotype.Component;

import com.manas.order_service.Entity.Order;
import com.manas.order_service.Model.OrderRequest;
import com.manas.order_service.Model.OrderResponse;

@Component
public class OrderMapper {
    
    public Order toOrder(OrderRequest req){
        return Order.builder()
        .customerId(req.customerId())
        .reference(req.reference())
        .totalPrice(req.totalPrice())
        .payMethodMode(req.payMethodMode())
        .build();
    }

    public OrderResponse toOrderResponse(Order order){
        return new OrderResponse(order.getId(), order.getReference(), order.getTotalPrice(), order.getCustomerId(), order.getPayMethodMode());
    }
}
