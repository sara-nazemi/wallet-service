package com.example.bootcampwalletservice.models.dtoes;

import com.example.bootcampwalletservice.models.entities.WalletStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WalletDto {
    private String userName;
    private Long balance;
    @NotNull(message = "wallet.walletcode.null")
    private String walletCode;

}
