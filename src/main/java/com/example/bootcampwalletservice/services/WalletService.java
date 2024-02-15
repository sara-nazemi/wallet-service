package com.example.bootcampwalletservice.services;

import com.example.bootcampwalletservice.models.entities.Wallet;
import com.example.bootcampwalletservice.repositories.WalletRepository;

public interface WalletService extends BaseService<Wallet, WalletRepository> {

    Wallet save(String userName);

    Wallet findByUserName(String userName);

    String generateWalletCode(String userName);

    Long getBalance(String userName);

    void removeWallet(String userName);
}
