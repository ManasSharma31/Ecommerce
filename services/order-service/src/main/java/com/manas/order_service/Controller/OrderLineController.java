package com.manas.order_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manas.order_service.Model.OrderLineResponse;
import com.manas.order_service.ServiceImpl.OrderLineService;

import lombok.RequiredArgsConstructor;
import java.util.*;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    
    private final OrderLineService orderLineService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLine(@PathVariable Integer orderId){
        return ResponseEntity.ok(orderLineService.getOrderLine(orderId));
    }
}
