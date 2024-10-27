package com.webclothes.webclothesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByOrderCode(String orderCode);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
    List<Order> findOrdersByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT o FROM Order o")
    List<Order> findAllOrders();



}
