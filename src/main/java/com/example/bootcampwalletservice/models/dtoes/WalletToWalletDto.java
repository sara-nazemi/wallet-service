package com.example.bootcampwalletservice.models.dtoes;

import com.example.bootcampwalletservice.models.entities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WalletToWalletDto {

    private String senderUserName;
    private String receiverUserName;
    private Long amount;
    private TransactionType type;
}
