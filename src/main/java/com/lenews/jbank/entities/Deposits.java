package com.lenews.jbank.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_deposits")
public class Deposits {

    @Id
    @Column(name = "deposit_id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID depositId;

    @ManyToOne
    @JoinColumn(name = "receiver_wallet_id")
    private Wallet receiver;



}
