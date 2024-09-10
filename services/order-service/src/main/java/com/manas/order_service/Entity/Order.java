package com.manas.order_service.Entity;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name="customer_order")
@EntityListeners(AuditingEntityListener.class)
public class Order{

    @Id
    @GeneratedValue
    private Integer id;
    private String reference;
    private BigDecimal totalPrice;
    private String customerId;
    @Enumerated(EnumType.STRING)
    private PayMethodMode payMethodMode;
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
    private List<OrderLine>orderLines;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime orderCreatedDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime orderModifiedDate;
    
}

//OneToMany @anno always have the mappedBy attributed which is equal to the name of the variable which we have used in the joining table.
//@EntityListeners(AuditingEntityListener.class) annotation is used in SPring boot project(JPA) for handling automatic auditing information.
//When we enable auditing in our project and use this annotation, fields like createdDate, lastModifiedDate, createdBy, and lastModifiedBy can be automatically populated by Spring Data JPA.