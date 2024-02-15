package com.example.bootcampwalletservice.repositories;

import com.example.bootcampwalletservice.models.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUserName(String userName);

    void deleteByUserName(String userName);
}
