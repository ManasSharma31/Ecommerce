package com.manas.notification_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.manas.notification_service.Email.EmailService;
import com.manas.notification_service.Entity.Notification;
import com.manas.notification_service.Kafka.Order.OrderConfirmation;
import com.manas.notification_service.Kafka.Payment.PaymentConfirmation;
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

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmation(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming payment confirmation from payment-topic TOPIC:: <{}>", paymentConfirmation);

        notificationRepository.save(Notification.builder()
                .createdAt(LocalDateTime.now()).paymentConfirmation(paymentConfirmation)
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .orderReference(paymentConfirmation.orderRefString()).build());
        String customerName = paymentConfirmation.customerFirstName() + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessfullEmail(paymentConfirmation.customerEmail(), customerName,
                paymentConfirmation.amount(), paymentConfirmation.orderRefString());

    }

    @KafkaListener(topics = "order-topic")
    public void consumePaymentConfirmation(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming order confirmation from order-topic TOPIC :: <{}>", orderConfirmation);

        notificationRepository.save(Notification.builder()
                .createdAt(LocalDateTime.now()).orderConfirmation(orderConfirmation)
                .type(NotificationType.ORDER_CONFIRMATION)
                .orderReference(orderConfirmation.reference()).build());
        String customerName = orderConfirmation.customer().firstName() + orderConfirmation.customer().lastName();
        emailService.sendOderConfirmationEmail(orderConfirmation.customer().email(), customerName,
                orderConfirmation.totalPrice(), orderConfirmation.reference(), orderConfirmation.products());

    }
}
