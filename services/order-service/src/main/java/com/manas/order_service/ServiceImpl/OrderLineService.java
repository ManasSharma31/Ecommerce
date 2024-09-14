package com.manas.order_service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.manas.order_service.Mapper.OrderLineMapper;
import com.manas.order_service.Model.OrderLineRequest;
import com.manas.order_service.Model.OrderLineResponse;
import com.manas.order_service.Repository.OrderLineRepository;

import lombok.AllArgsConstructor;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderLineService {
    
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
       return orderLineRepository.save(mapper.toOrderLine(orderLineRequest)).getId();
    }

    public List<OrderLineResponse> getOrderLine(String orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).toList();
    }


}
