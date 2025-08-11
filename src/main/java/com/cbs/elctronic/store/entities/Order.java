package com.cbs.elctronic.store.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private String billingName;

    @Column(length = 1000)
    private String billingAddress;

    private String billingPhone;

    private int orderAmount;

    private Date orderedDate;

    private Date deliveredDate;

    //PENDING,DISPATCHED,DELIVERED,
    //enum
    private String orderStatus;

    //NOT-PAID, PAID
    //enum
    private String paymentStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

}
