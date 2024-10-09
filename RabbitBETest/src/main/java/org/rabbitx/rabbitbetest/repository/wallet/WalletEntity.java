package org.rabbitx.rabbitbetest.repository.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallets")
@Data
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "walletname")
    private String walletName;

    @Column(name = "currentbalance")
    private double currentBalance;

    @Column(name = "transactionid")
    private int transactionId;

    @OneToMany(mappedBy = "wallet")
    private List<Transactions> transactions;

    @ManyToOne
    @JoinColumn(name = "walletid", nullable = false)
    private UserEntity walletuser;

    @Override
    public String toString(){
        return "{" + walletName + "}";
    }
}
