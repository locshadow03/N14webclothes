package com.webclothes.webclothesservice.service.order;

import java.util.List;

public interface IOrderService {
    Order createOrder(Customer customer, List<OrderItem> items);

    Order getOrder(Long orderId);

    List<Order> getOrdersByCustomerId(Long customerId);

    List<Order> getAllOrders();

    Order updateOrderStatus(Long orderId, String newStatus);

    Double calculateTotalAmount(List<OrderItem> items);

    void deleteOrder(Long orderId);


}
