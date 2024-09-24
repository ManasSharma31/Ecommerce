package com.example.payment_service.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    

    private final KafkaTemplate<String,PaymentNotification> kafkaTemplate;

    public void sendNotification(PaymentNotification paymentNotification){
        Message<PaymentNotification>message=MessageBuilder
        .withPayload(paymentNotification)
        .setHeader(KafkaHeaders.TOPIC, "payment-topic")
        .setHeader("__TypeId__", "paymentNotification") // Add type header for consumer deserialization
        .build();
        kafkaTemplate.send(message);
    }

}
