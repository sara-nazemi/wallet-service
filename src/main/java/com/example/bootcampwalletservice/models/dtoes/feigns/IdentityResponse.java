package com.example.bootcampwalletservice.models.dtoes.feigns;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IdentityResponse {
    private String message;
    private String code;
    private Date date;
    private Boolean hasError;
    private String data;
}
