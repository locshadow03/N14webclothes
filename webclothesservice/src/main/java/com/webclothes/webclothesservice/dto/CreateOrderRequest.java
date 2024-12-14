package com.webclothes.webclothesservice.dto;

import com.webclothes.webclothesservice.model.Customer;
import com.webclothes.webclothesservice.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Customer customer;
    private List<OrderItem> items;
}
