package com.example.payment_service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.example.payment_service.Model.PaymentRequest;
import com.example.payment_service.Repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import com.example.payment_service.Kafka.PaymentNotification;
import com.example.payment_service.Kafka.PaymentProducer;
import com.example.payment_service.Mapper.PaymentMapper;


@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final PaymentProducer PaymentProducer;
    public Integer createPayment(PaymentRequest paymentRequest) {
        
        Integer id=paymentRepository.save(mapper.toPayment(paymentRequest)).getId();
        PaymentProducer.sendNotification(new PaymentNotification(paymentRequest.orderReference(), 
        paymentRequest.amount()
        ,paymentRequest.paymentMode(), paymentRequest.customer().firstName(), paymentRequest.customer().lastName(), 
        paymentRequest.customer().email()));
        return id;
    }
    
}
