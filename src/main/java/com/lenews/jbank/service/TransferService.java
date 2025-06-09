package com.lenews.jbank.service;

import com.lenews.jbank.controller.TransferController;
import com.lenews.jbank.controller.dto.TransferRequestDto;
import com.lenews.jbank.entities.Transfer;
import com.lenews.jbank.entities.Wallet;
import com.lenews.jbank.exception.TransferException;
import com.lenews.jbank.exception.WalletNotFoundException;
import com.lenews.jbank.repository.TransferRepository;
import com.lenews.jbank.repository.WalletRepository;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransferService {
    // This class will handle the business logic for transfers between wallets.
    // It will include methods for initiating transfers, validating transfer amounts,
    // and updating wallet balances accordingly.

    // Example method signature:
    // public void initiateTransfer(Wallet sender, Wallet receiver, BigDecimal amount) {
    //     // Logic to handle the transfer
    // }
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void transferMoney(TransferRequestDto dto){

        var receiver = walletRepository.findById(dto.receiver())
                .orElseThrow(() -> new WalletNotFoundException("Receiver wallet not found"));
        var sender = walletRepository.findById(dto.sender())
                .orElseThrow(() -> new WalletNotFoundException("Sender wallet not found"));
        if (sender.getBalance().compareTo(dto.amountTransferred()) < 0) {
            throw new TransferException("Insufficient funds in sender's wallet. Your balance is "
                    + sender.getBalance() + " and you are trying to transfer " + dto.amountTransferred());
        }
        persistTransfer(dto, sender, receiver);
        updateWallets(dto, receiver, sender);
    }

    private void updateWallets(TransferRequestDto dto, Wallet receiver, Wallet sender) {
        receiver.setBalance(receiver.getBalance().add(dto.amountTransferred()));
        sender.setBalance(sender.getBalance().subtract(dto.amountTransferred()));
        walletRepository.save(receiver);
        walletRepository.save(sender);
    }

    private void persistTransfer(TransferRequestDto dto, Wallet sender, Wallet receiver) {
        var transfer = new Transfer();
        transfer.setAmountTransferred(dto.amountTransferred());
        transfer.setSender(sender);
        transfer.setReceiver(receiver);
        transfer.setTransferDate(LocalDateTime.now());
        // Update the sender's wallet balance
        transferRepository.save(transfer);
    }
}
