package com.manas.notification_service.Kafka;

import java.math.*;

import com.manas.notification_service.Model.PayMethodMode;

//this record should contains the same variables as that used in the  payment notification request otherwise there will be issues while desializing the object
public record PaymentConfirmation(

String orderRefString, 
BigDecimal amount,
PayMethodMode paymentMode,
String customerFirstName,
String customerLastName,
String customerEmail
) {
}
