package com.example.bootcampwalletservice.models.dtoes;

import com.example.bootcampwalletservice.models.entities.TransactionStatus;
import com.example.bootcampwalletservice.models.entities.TransactionType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WalletTransactionDto {
    private Long id;
    private Long amount;
    private TransactionStatus status;
    private LocalDate date;
    private TransactionType type;
    private Boolean deposit;
    private Boolean withdraw;
    private Long walletId;
}
