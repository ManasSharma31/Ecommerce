package com.manas.notification_service.Email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.manas.notification_service.Kafka.Order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.math.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;

    // The @Async annotation in Spring is used to mark a method as asynchronous.
    // When applied, it allows the method to run in a separate thread, so the caller
    // doesn't have to wait for the method to complete its execution.
    // This can be particularly useful when performing long-running tasks, like
    // sending an email, file processing, or making API calls, without blocking the
    // main thread.
    @Async
    public void sendPaymentSuccessfullEmail(String recipient, String customerName, BigDecimal amount,
            String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("sharmamanas442@gmail.com");
        final String template = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        try {
            String htmlContent = springTemplateEngine.process(template, context);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());
            messageHelper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            log.info(String.format("Payment confirmation email sent to: %s", recipient));
        } catch (MessagingException e) {
            log.warn("Cannnot send email to recipient", recipient);
        }
    }

    @Async
    public void sendOderConfirmationEmail(String recipient, String customerName, BigDecimal amount,
            String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("sharmamanas442@gmail.com");
        final String template = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        try {
            String htmlContent = springTemplateEngine.process(template, context);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
            messageHelper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            log.info(String.format("Order confirmation email sent to: %s", recipient));
        } catch (MessagingException e) {
            log.warn("Cannnot send email to recipient", recipient, e);
        }
    }
}
