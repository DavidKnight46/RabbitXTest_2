package org.rabbitx.rabbitbetest.repository.wallet;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "wallets")
@Data
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "walletname")
    private String walletName;

    @Column(name = "currentbalance")
    private double currentBalance;

    @OneToMany(mappedBy = "id")
    private List<Transactions> transactions;
}
