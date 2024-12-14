package com.webclothes.webclothesservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String message;
    private LocalDateTime createdDate;

    private String thank;
}
