package com.cipherLab.OrderMicroService.repository;

import com.cipherLab.OrderMicroService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
