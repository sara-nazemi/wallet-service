package com.example.bootcampwalletservice.services;

import com.example.bootcampwalletservice.models.entities.TransactionStatus;
import com.example.bootcampwalletservice.models.entities.TransactionType;
import com.example.bootcampwalletservice.models.entities.WalletTransaction;
import com.example.bootcampwalletservice.repositories.WalletTransactionRepository;

import java.time.LocalDate;
import java.util.List;

public interface WalletTransactionService extends BaseService<WalletTransaction, WalletTransactionRepository> {

    void deposit(String receiverUserName, Long amount, TransactionType type);

    void withdraw(String senderUserName, Long amount, TransactionType type);

    TransactionStatus walletToWallet(String receiverUserName, String senderUserName, Long amount, TransactionType type);

    List<WalletTransaction> showTransaction(String userName);

    List<WalletTransaction> showTransactionBetween(LocalDate startDate, LocalDate endDate, String userName);
}
