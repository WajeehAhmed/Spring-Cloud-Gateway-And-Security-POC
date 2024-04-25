package com.cipherLab.OrderMicroService.service;

import com.cipherLab.OrderMicroService.dto.OrderEvent;
import com.cipherLab.OrderMicroService.dto.OrderRequestDto;
import com.cipherLab.OrderMicroService.dto.OrderStatus;
import com.cipherLab.OrderMicroService.entity.Order;
import com.cipherLab.OrderMicroService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderEvent createBookOrder(OrderRequestDto orderRequestDto) {
        var newOrder = new Order();
        newOrder.setBookId(orderRequestDto.getBookId());
        newOrder.setUserId(orderRequestDto.getUserId());
        newOrder.setLastChangeTs(LocalDateTime.now());
        newOrder.setStatus(OrderStatus.CREATED);
        var createdOrder = orderRepository.save(newOrder);

        orderRequestDto.setOrderId(createdOrder.getId());
        var orderEvent = new OrderEvent();
        orderEvent.setOrderStatus(OrderStatus.CREATED);
        orderEvent.setOrderRequestDto(orderRequestDto);
        return orderEvent;
    }
}
