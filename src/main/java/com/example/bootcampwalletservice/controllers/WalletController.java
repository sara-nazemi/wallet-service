package com.example.bootcampwalletservice.controllers;

import com.example.bootcampwalletservice.converters.WalletConverter;
import com.example.bootcampwalletservice.models.dtoes.WalletDto;
import com.example.bootcampwalletservice.models.dtoes.feigns.IdentityResponse;
import com.example.bootcampwalletservice.models.responseFormat.WalletResponse;
import com.example.bootcampwalletservice.serviceexception.WalletException;
import com.example.bootcampwalletservice.services.WalletService;
import com.example.bootcampwalletservice.services.feigns.JwtServiceCall;
import com.example.bootcampwalletservice.util.ResourceBundleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletConverter walletConverter;
    @Autowired
    private JwtServiceCall jwtServiceCall;
    @Autowired
    private ResourceBundleUtil resourceBundleUtil;

    @PostMapping("/sava")
    public WalletResponse<?> createWallet(@RequestHeader String token, @RequestHeader("lang") String lang) throws WalletException {
        IdentityResponse usernameFromToken = jwtServiceCall.getUsernameFromToken(token, lang);
        WalletDto walletDto1 = walletConverter.convertDto(walletService.save(usernameFromToken.getData()));
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage("operation.successful.run", lang))
                .code("operation.successful.run")
                .date(new Date())
                .hasError(false)
                .data(walletDto1)
                .build();
    }

    @GetMapping("/showbalance")
    public WalletResponse<?> showBalance(@RequestBody WalletDto dto, @RequestHeader("lang") String lang) throws WalletException{
        Long balance = walletService.getBalance(dto.getUserName());
        return WalletResponse.builder()
                .message(resourceBundleUtil.getMessage("operation.successful.run", lang))
                .code("operation.successful.run")
                .date(new Date())
                .data(balance)
                .hasError(false)
                .build();
    }

    @DeleteMapping("/delete/{userName}")
    public void removeWallet(@PathVariable String userName, @RequestHeader("lang") String lang) throws WalletException {
        walletService.removeWallet(userName);
    }


}
