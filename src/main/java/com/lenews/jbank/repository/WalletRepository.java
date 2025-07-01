package com.lenews.jbank.repository;

import com.lenews.jbank.entities.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    String SQL_STATEMENT_VIEW = """
            SELECT
            	BIN_TO_UUID(transfer_id) as statement_id,
                "transfer" as type,
                amount_transferred as statement_value,
                BIN_TO_UUID(receiver_wallet_id) as receiver_wallet,
                BIN_TO_UUID(sender_wallet_id) as sender_wallet,
                transfer_date as date_time
            FROM
            	tb_transfers
            WHERE receiver_wallet_id = UUID_TO_BIN(?1) OR sender_wallet_id = UUID_TO_BIN(?1)
            UNION ALL
            SELECT
            	BIN_TO_UUID(deposit_id) as statement_id,
                "deposit" as type,
                amount_deposited as statement_value,
                BIN_TO_UUID(wallet_id) as receiver_wallet,
                "" as sender_wallet,
                deposit_date as date_time
            FROM tb_deposits
            WHERE
                wallet_id = UUID_TO_BIN(?1)
            """;

    String SQL_COUNT_STATEMENT = """
           SELECT COUNT(*) FROM (
           """ + SQL_STATEMENT_VIEW + """
            ) as TOTAL
            """;

    Optional<Wallet> findByCpfOrEmail(String cpf, String email);

    @Query(value = SQL_STATEMENT_VIEW,
            countQuery = SQL_COUNT_STATEMENT ,
            nativeQuery = true)
    Page<StatementView> findStatements(String walletId, PageRequest pageRequest);

}

