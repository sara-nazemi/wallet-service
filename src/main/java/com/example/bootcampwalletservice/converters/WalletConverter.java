package com.example.bootcampwalletservice.converters;

import com.example.bootcampwalletservice.models.dtoes.WalletDto;
import com.example.bootcampwalletservice.models.entities.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletConverter extends BaseConverter<Wallet, WalletDto>{
}
