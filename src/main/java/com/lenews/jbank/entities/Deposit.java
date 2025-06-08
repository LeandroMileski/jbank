package com.lenews.jbank.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_deposits")
public class Deposit {

    @Id
    @Column(name = "deposit_id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID depositId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(name = "amount_deposited")
    private BigDecimal ammountDeposited;

    @Column(name = "deposit_date")
    private LocalDateTime depositDate;

    @Column(name = "ip_address")
    private String ipAddress;

    public Deposit() {
    }

    public UUID getDepositId() {
        return depositId;
    }

    public void setDepositId(UUID depositId) {
        this.depositId = depositId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public BigDecimal getAmmountDeposited() {
        return ammountDeposited;
    }

    public void setAmmountDeposited(BigDecimal ammountDeposited) {
        this.ammountDeposited = ammountDeposited;
    }

    public LocalDateTime getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDateTime depositDate) {
        this.depositDate = depositDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
