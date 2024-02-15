package com.example.bootcampwalletservice.converters;

import com.example.bootcampwalletservice.models.dtoes.WalletTransactionDto;
import com.example.bootcampwalletservice.models.entities.WalletTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletTransactionConverter extends BaseConverter<WalletTransaction, WalletTransactionDto> {
}
