package com.lenews.jbank.service;

import com.lenews.jbank.controller.dto.*;
import com.lenews.jbank.entities.Deposit;
import com.lenews.jbank.entities.Wallet;
import com.lenews.jbank.exception.DeleteWalletException;
import com.lenews.jbank.exception.StatementException;
import com.lenews.jbank.exception.WalletDataAlreadyExistsException;
import com.lenews.jbank.exception.WalletNotFoundException;
import com.lenews.jbank.repository.DepositRepository;
import com.lenews.jbank.repository.StatementView;
import com.lenews.jbank.repository.WalletRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final DepositRepository depositRepository;

    public WalletService(WalletRepository walletRepository, DepositRepository depositRepository) {
        this.walletRepository = walletRepository;
        this.depositRepository = depositRepository;
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

    @Transactional
    public void depositWallet(UUID walletId, DepositMoneyDto dto, String ipAddress) {

        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with ID: " + walletId));

        var deposit = new Deposit();
        deposit.setWallet(wallet);
        deposit.setAmmountDeposited(dto.amountDeposited());
        deposit.setIpAddress(ipAddress);
        deposit.setDepositDate(LocalDateTime.now());
        depositRepository.save(deposit);

        wallet.setBalance(wallet.getBalance().add(dto.amountDeposited()));
        walletRepository.save(wallet);

    }


    public StatementDto getStatement(UUID walletId, Integer page, Integer pageSize) {

        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with ID: " + walletId));

        var pageRequest = PageRequest.of(page, pageSize, Sort.Direction.DESC, "date_time");

        var statements =  walletRepository.findStatements(walletId.toString(), pageRequest)
                .map(view -> mapToDto(walletId, view));

        return new StatementDto(
                new WalletDto(wallet.getWalletId(), wallet.getCpf(), wallet.getName(),wallet.getEmail(), wallet.getBalance()),
                    statements.getContent(),
                new PaginationDto(statements.getNumber(), statements.getSize(), statements.getTotalElements(), statements.getTotalPages())
        );
    }

    private StatementItemDto mapToDto(UUID walletId, StatementView view) {

        if(view.getType().equalsIgnoreCase("deposit")){
            return mapToDeposit(view);
        }

        if(view.getType().equalsIgnoreCase("transfer")) {
            if (view.getReceiverWallet().equals(walletId.toString())) {
                return mapWhenTransferReceived(view);
            } else if (view.getSenderWallet().equals(walletId.toString())) {
                return mapWhenTransferSent(view);
            }
        }

        throw new StatementException("Invalid statement type: " + view.getType());
    }

    private StatementItemDto mapWhenTransferSent(StatementView view) {
        return new StatementItemDto(
                view.getStatementId(),
                view.getType(),
                "money transfer sent",
                view.getStatementValue(),
                view.getDateTime(),
                StatementOperation.DEBIT
        );
    }

    private StatementItemDto mapWhenTransferReceived(StatementView view) {
        return new StatementItemDto(
                view.getStatementId(),
                view.getType(),
                "money transfer received",
                view.getStatementValue(),
                view.getDateTime(),
                StatementOperation.CREDIT
        );
    }

    private StatementItemDto mapToDeposit(StatementView view) {
        return new StatementItemDto(
                view.getStatementId(),
                view.getType(),
                "money deposit",
                view.getStatementValue(),
                view.getDateTime(),
                StatementOperation.CREDIT
        );
    }
}


