package org.rabbitx.rabbitbetest.repository.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;

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

    @Column
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "walletID", nullable = false)
    private UserEntity walletUser;

    @OneToMany(mappedBy = "")
    private List<Transactions> transactions;
}
