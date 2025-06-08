package com.lenews.jbank.controller;

import com.lenews.jbank.controller.dto.CreateWalletDto;
import com.lenews.jbank.entities.Wallet;
import com.lenews.jbank.exception.WalletDataAlreadyExistsException;
import com.lenews.jbank.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var walletDb = walletRepository.findByCpfOrEmail(dto.cpf(), dto.email());
        if (walletDb.isPresent()){
            throw new WalletDataAlreadyExistsException("Wallet already exists for CPF or Email");
        }

        var wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setName(dto.name());
        wallet.setCpf(dto.cpf());
        wallet.setEmail(dto.email());

        return walletRepository.save(wallet);

    }
}
