package com.manas.notification_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.manas.notification_service.Email.EmailService;
import com.manas.notification_service.Entity.Notification;
import com.manas.notification_service.Kafka.Order.OrderNotification;
import com.manas.notification_service.Kafka.Payment.PaymentNotification;
import com.manas.notification_service.Model.NotificationType;
import com.manas.notification_service.Repository.NotificationRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic",groupId = "paymentGroup")
    public void consumePaymentConfirmation(PaymentNotification paymentNotification) throws MessagingException {
        log.info("Consuming payment confirmation from payment-topic TOPIC:: <{}>", paymentNotification);

        notificationRepository.save(Notification.builder()
                .createdAt(LocalDateTime.now()).paymentConfirmation(paymentNotification)
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .orderReference(paymentNotification.orderRefString()).build());
        String customerName = paymentNotification.customerFirstName() + paymentNotification.customerLastName();
        emailService.sendPaymentSuccessfullEmail(paymentNotification.customerEmail(), customerName,
        paymentNotification.amount(), paymentNotification.orderRefString());

    }

    @KafkaListener(topics = "order-topic",groupId="orderGroup")
    public void consumeOrderConfirmation(OrderNotification orderNotification) throws MessagingException {
        log.info("Consuming order confirmation from order-topic TOPIC :: <{}>", orderNotification);

        notificationRepository.save(Notification.builder()
                .createdAt(LocalDateTime.now()).orderConfirmation(orderNotification)
                .type(NotificationType.ORDER_CONFIRMATION)
                .orderReference(orderNotification.reference()).build());
        String customerName = orderNotification.customer().firstName() + orderNotification.customer().lastName();
        emailService.sendOderConfirmationEmail(orderNotification.customer().email(), customerName,
        orderNotification.totalPrice(), orderNotification.reference(), orderNotification.products());

    }
}
