package com.lenews.jbank.repository;

import com.lenews.jbank.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByCpfOrEmail(String cpf, String email);
}

