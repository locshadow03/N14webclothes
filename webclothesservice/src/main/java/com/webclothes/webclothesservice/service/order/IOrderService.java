package com.webclothes.webclothesservice.service.order;

import com.webclothes.webclothesservice.dto.CustomerTotalAmountDto;
import com.webclothes.webclothesservice.dto.ProductResponse;
import com.webclothes.webclothesservice.model.Customer;
import com.webclothes.webclothesservice.model.Order;
import com.webclothes.webclothesservice.model.OrderItem;

import java.util.List;

public interface IOrderService {
    Order createOrder(Customer customer, List<OrderItem> items);

    Order getOrder(Long orderId);

    List<Order> getOrdersByCustomerId(Long customerId);

    List<Order> getAllOrders();

    Order updateOrderStatus(Long orderId, String newStatus);

    Double calculateTotalAmount(List<OrderItem> items);

    void deleteOrder(Long orderId);

    long getOrdersCountToday();

    long getOrdersCountThisMonth();

    long getOrdersCountThisYear();

    long getTotalOrdersCount();

    Double getTotalAmountToday();

    Double getTotalAmountMonth();

    Double getTotalAmountYear();

    Double getTotalAmount();

    List<CustomerTotalAmountDto> getTopCustomersByTotalAmountByToday();

    List<CustomerTotalAmountDto> getTopCustomersByTotalAmountByCurrentMonth();

    List<CustomerTotalAmountDto> getTopCustomersByTotalAmountByCurrentYear();

    List<CustomerTotalAmountDto> getTopCustomersByTotalAmountOverall();

    List<ProductResponse> getTopProductByToday();

    List<ProductResponse> getTopProductByMonth();

    List<ProductResponse> getTopProductByYear();

    List<ProductResponse> getTopProductByAll();

    List<Double> getMonthlyRevenue(int year);

}
