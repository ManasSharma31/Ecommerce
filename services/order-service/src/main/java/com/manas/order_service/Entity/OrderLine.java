package com.manas.order_service.Entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderLine {

    @Id
    @GeneratedValue
    public Integer id;
    @ManyToOne
    @JoinColumn(name="order_id")
    public Order order;
    private Integer productId;
    private Integer quantity;
}

//ManyToOne always contain the join column condition and contains that extra column 
