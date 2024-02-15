package com.example.bootcampwalletservice.models.responseFormat;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WalletResponse<T> {
    private String message;
    private String code;
    private Date date;
    private Boolean hasError;
    private T data;
}
