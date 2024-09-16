package com.manas.order_service.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.manas.order_service.Customer.CustomerResponse;
import com.manas.order_service.Customer.FeignCustomerService;
import com.manas.order_service.Entity.Order;
import com.manas.order_service.Exception.BusinessException;
import com.manas.order_service.Kakfa.OrderConfirmation;
import com.manas.order_service.Kakfa.OrderProducer;
import com.manas.order_service.Model.OrderLineRequest;
import com.manas.order_service.Model.OrderRequest;
import com.manas.order_service.Model.OrderResponse;
import com.manas.order_service.Payment.PaymentClient;
import com.manas.order_service.Payment.PaymentRequest;
import com.manas.order_service.Product.ProductClient;
import com.manas.order_service.Product.ProductPurchaseReponse;
import com.manas.order_service.Product.ProductPurchaseRequest;
import com.manas.order_service.Repository.OrderRepository;

import lombok.AllArgsConstructor;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderService {

    private final FeignCustomerService customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

        //check if the customer is valid 
        Optional<CustomerResponse>customer=customerClient.getCustomerById(request.customerId());        
        if(!customer.isPresent()) {
            throw new BusinessException("Customer is not present with id " + request.customerId());
        }
        //check the purchase product is valid
        List<ProductPurchaseReponse>productResponse=productClient.getProducts(request.products());
        //save the order in the db
        Order order=orderRepository.save(mapper.map(request, Order.class));

        //persist the orderline
        for(ProductPurchaseRequest req:request.products()){
            orderLineService.saveOrderLine(
                new OrderLineRequest(null, 
                order.getId(),
                req.productId(),
                req.quantity()
            ));            
        }

        //process payment

        paymentClient.requestOrderPayment(new PaymentRequest(request.totalPrice(), 
             request.payMethodMode(),order.getId(),order.getReference(),customer.get()));
        //send messgae to the notification ms

        orderProducer.sendMessage(new OrderConfirmation(request.reference(), request.totalPrice(), productResponse, request.payMethodMode(), customer.get()));


        return order.getId();
    }

    public OrderResponse getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).map(order->mapper.map(order,OrderResponse.class))
        .orElseThrow(()->new BusinessException("Could not find order"));
    }



    
}
