package com.example.bootcampwalletservice.services;

import com.example.bootcampwalletservice.models.entities.TransactionStatus;
import com.example.bootcampwalletservice.models.entities.TransactionType;
import com.example.bootcampwalletservice.models.entities.Wallet;
import com.example.bootcampwalletservice.models.entities.WalletTransaction;
import com.example.bootcampwalletservice.repositories.WalletRepository;
import com.example.bootcampwalletservice.repositories.WalletTransactionRepository;
import com.example.bootcampwalletservice.serviceexception.WalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WalletTransactionServiceImpl extends BaseServiceImpl<WalletTransaction, WalletTransactionRepository> implements WalletTransactionService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletTransactionRepository walletTransactionRepository;
    @Autowired
    private WalletServiceImpl walletServiceImpl;

    public WalletTransaction setWalletTransaction(Wallet wallet, Boolean deposit, Boolean withdraw,
                                                  TransactionType type, Long amount) {
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .walletId(wallet)
                .date(LocalDate.now())
                .amount(amount)
                .type(type)
                .status(TransactionStatus.PEND)
                .deposit(deposit)
                .withdraw(withdraw)
                .phoneNumber(wallet.getUserName())
                .build();
        insert(walletTransaction);
        return walletTransaction;
    }

    public void deposit(String receiverUserName, Long amount, TransactionType type) {
        if (amount == null) {
            throw new WalletException("amount is null");
        }
        walletServiceImpl.checkingExistWallet(receiverUserName);

        if (!(type == TransactionType.WALLET_TO_WALLET || type == TransactionType.QR_CODE ||
                type == TransactionType.BANK_TO_WALLET || type == TransactionType.WALLET_TO_CHARITY ||
                type == TransactionType.AUTO_CHARGE)) {
            throw new WalletException("The option is not correct");
        }

        Wallet byUserName = walletRepository.findByUserName(receiverUserName);

        byUserName.setBalance(byUserName.getBalance() + amount);
        walletRepository.save(byUserName);

    }


    public void withdraw(String senderUseName, Long amount, TransactionType type) {
        walletServiceImpl.checkingExistWallet(senderUseName);

        if (!(type == TransactionType.WALLET_TO_WALLET || type == TransactionType.WALLET_TO_BANK ||
                type == TransactionType.WALLET_TO_CHARITY || type == TransactionType.QR_CODE)) {
            throw new WalletException("The option is not correct");
        }
        Wallet byUserName = walletRepository.findByUserName(senderUseName);
        if (byUserName.getBalance() < amount) {
            throw new WalletException("Insufficient inventory");
        }
        byUserName.setBalance(byUserName.getBalance() - amount);
        walletRepository.save(byUserName);
    }

    @Override
    public TransactionStatus walletToWallet(String receiverUserName, String senderUserName, Long
            amount, TransactionType type) {
        walletServiceImpl.checkingExistWallet(receiverUserName);
        walletServiceImpl.checkingExistWallet(senderUserName);
        if (!(type == TransactionType.WALLET_TO_WALLET || type == TransactionType.WALLET_TO_CHARITY)) {
            throw new WalletException("The option is not correct");
        }
        withdraw(senderUserName, amount, type);
        WalletTransaction withdraw = setWalletTransaction(walletRepository.findByUserName(senderUserName), false, true,
                type, amount);

        deposit(receiverUserName, amount, type);

        WalletTransaction deposit = setWalletTransaction(walletRepository.findByUserName(receiverUserName), true, false,
                type, amount);
        withdraw.setStatus(TransactionStatus.SUCCESS);
        deposit.setStatus(TransactionStatus.SUCCESS);

        if (withdraw.getStatus().equals(TransactionStatus.SUCCESS) && deposit.getStatus().equals(TransactionStatus.SUCCESS)) {
            return TransactionStatus.SUCCESS;
        }
        return TransactionStatus.FAILED;
    }


    public List<WalletTransaction> showTransaction(String userName) {
        walletServiceImpl.checkingExistWallet(userName);
        return walletTransactionRepository.findAllByUserName(userName);
    }


    public List<WalletTransaction> showTransactionBetween(LocalDate startDate, LocalDate endDate, String userName) {

        if (startDate == null || endDate == null || userName == null) {
            throw new WalletException("items.not.null");
        }
        walletServiceImpl.checkingExistWallet(userName);
        List<WalletTransaction> transactions = new ArrayList<>();
        List<WalletTransaction> walletTransactions = showTransaction(userName);
        for (WalletTransaction walletTransaction : walletTransactions) {
            if (walletTransaction.getDate().isAfter(startDate) && walletTransaction.getDate().isBefore(endDate)) {
                transactions.add(walletTransaction);
            }
        }
        return transactions;
    }

}
