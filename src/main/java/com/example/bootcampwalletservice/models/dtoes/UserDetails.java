package com.example.bootcampwalletservice.models.dtoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDetails {
    private String userName;
    private String token;
}
