package com.example.bootcampwalletservice.controllers;

import com.example.bootcampwalletservice.converters.WalletTransactionConverter;
import com.example.bootcampwalletservice.models.dtoes.ShowTransactionBetweenDto;
import com.example.bootcampwalletservice.models.dtoes.WalletToWalletDto;
import com.example.bootcampwalletservice.models.dtoes.WalletTransactionDto;
import com.example.bootcampwalletservice.models.entities.TransactionStatus;
import com.example.bootcampwalletservice.models.entities.WalletTransaction;
import com.example.bootcampwalletservice.models.responseFormat.WalletResponse;
import com.example.bootcampwalletservice.serviceexception.WalletException;
import com.example.bootcampwalletservice.services.WalletTransactionService;
import com.example.bootcampwalletservice.util.ResourceBundleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wallettransaction")
public class WalletTransactionController {

    @Autowired
    private WalletTransactionService walletTransactionService;
    @Autowired
    private ResourceBundleUtil resourceBundleUtil;
    @Autowired
    private WalletTransactionConverter walletTransactionConverter;

    @PostMapping("/wallettowallet")
    public WalletResponse<?> walletToWallet(@RequestBody WalletToWalletDto wallet, @RequestHeader("lang") String lang) throws WalletException {
        TransactionStatus transactionStatus = walletTransactionService.walletToWallet(wallet.getSenderUserName(), wallet.getReceiverUserName(),
                wallet.getAmount(), wallet.getType());
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage("operation.successful.run", lang))
                .code("operation.successful.run")
                .date(new Date())
                .data(transactionStatus)
                .hasError(false)
                .build();
    }

    @GetMapping("/showtransaction/{userName}")
    public WalletResponse<?> showTransaction(@PathVariable String userName, @RequestHeader("lang") String lang) {
        List<WalletTransactionDto> walletTransactionDtos = walletTransactionConverter.convertDtoes(walletTransactionService.showTransaction(userName));
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage("operation.successful.run", lang))
                .code("operation.successful.run")
                .date(new Date())
                .data(walletTransactionDtos)
                .hasError(false)
                .build();
    }

    @GetMapping("/showtransactionbetween")
    public WalletResponse<?> showTransactionBetween(@RequestBody ShowTransactionBetweenDto dto, @RequestHeader("lang") String lang) {
        List<WalletTransaction> walletTransactions = walletTransactionService.showTransactionBetween(dto.getStartDate(), dto.getEndDate(), dto.getUserName());
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage("operation.successful.run", lang))
                .code("operation.successful.run")
                .date(new Date())
                .hasError(false)
                .data(walletTransactions)
                .build();
    }
}
