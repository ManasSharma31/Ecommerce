
package com.manas.notification_service.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.manas.notification_service.Kafka.Order.OrderNotification;
import com.manas.notification_service.Kafka.Payment.PaymentNotification;
import com.manas.notification_service.Model.NotificationType;

import lombok.Builder;
import lombok.Data;
import java.time.*;


@Document
@Data
@Builder
public class Notification {
    
    @Id
    private String id;
    String orderReference;
    private NotificationType type;
    private LocalDateTime createdAt;
    private OrderNotification orderConfirmation;
    private PaymentNotification paymentConfirmation;
}
