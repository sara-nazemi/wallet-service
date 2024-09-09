package com.example.bootcampwalletservice.services;

import com.example.bootcampwalletservice.models.entities.Wallet;
import com.example.bootcampwalletservice.models.entities.WalletStatus;
import com.example.bootcampwalletservice.repositories.WalletRepository;
import com.example.bootcampwalletservice.serviceexception.WalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WalletServiceImpl extends BaseServiceImpl<Wallet, WalletRepository> implements WalletService {
    private final WalletRepository walletRepository;


    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet save(String userName) {
        if (userName.isEmpty()) {
            throw new WalletException("phone.number.null");
        }
        return walletRepository.save(Wallet.builder()
                .userName(userName)
                .balance(0L)
                .walletStatus(WalletStatus.CREATED)
                .walletCode(generateWalletCode(userName))
                .build());
    }

    @Override
    public Wallet findByUserName(String userName) {
        checkingExistWallet(userName);
        return walletRepository.findByUserName(userName);
    }

    public String generateWalletCode(String userName) {
        int leftLimit = 97; //letter 'a'
        int rightLimit = 122; //letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit, targetStringLength + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString + userName;
    }

    public Long getBalance(String userName) {
        Wallet byUserName = new Wallet();
        checkingExistWallet(userName);
        return byUserName.getBalance();
    }

    public void checkingExistWallet(String userName) {
        if (userName.isEmpty()) {
            throw new WalletException("phone.number.null");
        }
        Wallet byUserName = walletRepository.findByUserName(userName);
        if (byUserName == null) {
            throw new WalletException("wallet.not.available");
        }
        if (!userName.equals(byUserName.getUserName())) {
            throw new WalletException("phoneNumber.not.found");
        }
    }

    public void removeWallet(String userName){
        checkingExistWallet(userName);
        checkingExistWallet(userName);
        walletRepository.deleteByUserName(userName);

    }



}
