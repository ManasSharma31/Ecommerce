package com.manas.notification_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.manas.notification_service.Entity.Notification;
import com.manas.notification_service.Model.NotificationType;
import com.manas.notification_service.Repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    
    private final NotificationRepository  notificationRepository;
    
    @KafkaListener(topics="payment-topic")
    public void consumePaymentConfirmation(PaymentConfirmation paymentConfirmation){
        log.info("Consuming payment confirmation from TOPIC payment-topic :: <{}>", paymentConfirmation);

        notificationRepository.save(Notification.builder()
        .createdAt(LocalDateTime.now()).paymentConfirmation(paymentConfirmation).type(NotificationType.PAYMENT_CONFIRMATION)
        .orderReference(paymentConfirmation.orderRefString()).
        build());
    }

    @KafkaListener(topics="order-topic")
    public void consumePaymentConfirmation(OrderConfirmation orderConfirmation){
        log.info("Consuming order confirmation from TOPIC order-topic :: <{}>", orderConfirmation);

        notificationRepository.save(Notification.builder()
        .createdAt(LocalDateTime.now()).orderConfirmation(orderConfirmation).type(NotificationType.ORDER_CONFIRMATION)
        .orderReference(orderConfirmation.reference()).
        build());
    }
}
