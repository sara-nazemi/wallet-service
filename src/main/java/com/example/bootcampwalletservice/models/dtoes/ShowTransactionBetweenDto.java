package com.example.bootcampwalletservice.models.dtoes;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShowTransactionBetweenDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;
}
