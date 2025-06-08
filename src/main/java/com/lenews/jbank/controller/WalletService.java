package com.lenews.jbank.controller;

import com.lenews.jbank.controller.dto.CreateWalletDto;
import com.lenews.jbank.entities.Wallet;
import com.lenews.jbank.exception.DeleteWalletException;
import com.lenews.jbank.exception.WalletDataAlreadyExistsException;
import com.lenews.jbank.repository.WalletRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

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

    public boolean deleteWallet(UUID walletId) {
        var wallet = walletRepository.findById(walletId);

        if(wallet.isPresent()) {
            if (wallet.get().getBalance().compareTo(BigDecimal.ZERO) != 0) {
                throw new DeleteWalletException("Cannot delete wallet with non-zero balance. Current balance: " + wallet.get().getBalance());
            }
            walletRepository.deleteById(walletId);
        }
        return wallet.isPresent();
    }
}
