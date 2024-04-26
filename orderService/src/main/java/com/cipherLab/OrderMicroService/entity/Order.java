package com.cipherLab.OrderMicroService.entity;

import com.cipherLab.OrderMicroService.dto.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookOrders")
public class Order {
    private @Id
    @GeneratedValue Long id;
    private Long bookId;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime LastChangeTs;

}
