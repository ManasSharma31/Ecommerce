
package com.manas.notification_service.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.manas.notification_service.Kafka.Order.OrderConfirmation;
import com.manas.notification_service.Kafka.Payment.PaymentConfirmation;
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
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
