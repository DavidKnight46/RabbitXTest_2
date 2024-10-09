package org.rabbitx.rabbitbetest.repository.wallet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;

import java.util.List;

@Entity
@Table(name = "wallets")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Column(name = "walletname")
    @Getter
    @Setter
    private String walletName;

    @Column(name = "currentbalance")
    @Getter
    @Setter
    private double currentBalance;

    @Column(name = "transactionid")
    @Getter
    @Setter
    private int transactionId;

    @OneToMany(mappedBy = "wallet")
    @Getter
    @Setter
    private List<Transactions> transactions;

    @ManyToOne
    @JoinColumn(name = "walletid", nullable = false)
    @Setter
    private UserEntity walletuser;

    @Override
    public String toString(){
        return "{" + walletName + "}";
    }
}
