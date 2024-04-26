package com.cipherLab.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private UUID eventId=UUID.randomUUID();
    private final Date eventDate=new Date();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;
}
