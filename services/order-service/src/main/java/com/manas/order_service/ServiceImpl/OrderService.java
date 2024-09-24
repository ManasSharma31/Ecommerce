package com.manas.order_service.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.manas.order_service.Customer.CustomerResponse;
import com.manas.order_service.Customer.FeignCustomerService;
import com.manas.order_service.Entity.Order;
import com.manas.order_service.Exception.BusinessException;
import com.manas.order_service.Exception.PaymentException;
import com.manas.order_service.Kafka.OrderNotification;
import com.manas.order_service.Kafka.OrderProducer;
import com.manas.order_service.Mapper.OrderMapper;
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
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final FeignCustomerService customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        log.info("Order request is  <{}>",request);
        //check if the customer is valid 
        Optional<CustomerResponse>customer=customerClient.getCustomerById(request.customerId());        
        if(!customer.isPresent()) {
            throw new BusinessException("Customer is not present with id " + request.customerId());
        }
        //check the purchase product is valid
        List<ProductPurchaseReponse>productResponse=productClient.getProducts(request.products());
        //save the order in the db
        Order order=orderRepository.save(mapper.toOrder(request));

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

        Integer paymentId=paymentClient.requestOrderPayment(new PaymentRequest(request.totalPrice(), 
        request.payMethodMode(),order.getId(),order.getReference(),customer.get()));
        //send messgae to the notification ms

        if(paymentId==-1){
            throw new PaymentException("Payment failed for order id " + order.getId());
        }
        CustomerResponse validCustomer=customer.get();
        orderProducer.sendMessage(new OrderNotification(request.reference(), request.totalPrice(), productResponse, request.payMethodMode(), validCustomer));


        return order.getId();
    }

    public OrderResponse getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).map(order->mapper.toOrderResponse(order))
        .orElseThrow(()->new BusinessException("Could not find order"));
    }



    
}
