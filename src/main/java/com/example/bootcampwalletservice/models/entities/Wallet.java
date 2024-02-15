package com.example.bootcampwalletservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "WALLETSERVICE")
public class Wallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "USERNAME", unique = true)
    private String userName;
    @Column(name = "BALANCE")
    private Long balance;
    @Column(name = "WALLETCODE")
    private String walletCode;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private WalletStatus walletStatus;
    @OneToMany(mappedBy = "walletId", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<WalletTransaction> transactions;


}
