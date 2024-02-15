package com.example.bootcampwalletservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "WALLETTRANSACTION")
public class WalletTransaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "DATE")
    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private TransactionStatus status;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "TYPE")
    private TransactionType type;
    @Column(name = "DEPOSIT")
    private Boolean deposit;
    @Column(name = "WITHDRAW")
    private Boolean withdraw;
    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "WALLETID")
    private Wallet walletId;

}
