package org.rabbitx.rabbitbetest.repository.user;

import jakarta.persistence.*;
import lombok.Data;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "walletid")
    private int walletId;

    @Column(name = "positionid")
    private int positionId;

    @OneToMany(mappedBy = "walletuser")
    private List<WalletEntity> wallets;

    @OneToMany(mappedBy = "positionuser")
    private List<PositionEntity> positions;

    @Override
    public String toString(){
        return "{" + userName + "}";
    }

}
