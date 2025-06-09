package com.lenews.jbank.repository;

import com.lenews.jbank.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    // This interface will inherit all CRUD operations from JpaRepository
    // Additional custom query methods can be defined here if needed

}
