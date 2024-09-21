package com.manas.notification_service.Email;

import lombok.Getter;

public enum EmailTemplates {
    
    PAYMENT_CONFIRMATION("payemnt-confirmation.html","Payment successful processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order confirmed");


    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

}
