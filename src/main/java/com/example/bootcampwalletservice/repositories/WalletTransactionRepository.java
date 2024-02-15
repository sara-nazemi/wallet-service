package com.example.bootcampwalletservice.repositories;

import com.example.bootcampwalletservice.models.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

    List<WalletTransaction> findAllByUserName(String userName);
}
