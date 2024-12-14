package com.webclothes.webclothesservice.dto;

import com.webclothes.webclothesservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTotalAmountDto {
    private Customer customer;
    private Double totalAmount;
}

