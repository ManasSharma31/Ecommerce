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
    

    private final KafkaTemplate<String,PaymemtNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymemtNotificationRequest request){
        Message<PaymemtNotificationRequest>message=MessageBuilder
        .withPayload(request)
        .setHeader(KafkaHeaders.TOPIC, "payment-topic")
        .build();
        kafkaTemplate.send(message);
    }

}
