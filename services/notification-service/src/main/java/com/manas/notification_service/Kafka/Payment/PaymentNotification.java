package com.manas.notification_service.Kafka.Payment;

import java.math.*;

//this record should contains the same variables as that used in the  payment notification request otherwise there will be issues while desializing the object
public record PaymentNotification(

String orderRefString, 
BigDecimal amount,
PaymentMode paymentMode,
String customerFirstName,
String customerLastName,
String customerEmail
) {
}
