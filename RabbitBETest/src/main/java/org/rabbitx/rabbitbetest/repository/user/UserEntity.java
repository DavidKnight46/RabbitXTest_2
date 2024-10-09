package org.rabbitx.rabbitbetest.repository.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Column(name = "username")
    @Getter
    @Setter
    private String userName;

    @Column(name = "walletid")
    @Getter
    @Setter
    private int walletId;

    @Column(name = "positionid")
    @Getter
    @Setter
    private int positionId;

    @OneToMany(mappedBy = "walletuser")
    @Getter
    @Setter
    private List<WalletEntity> wallets;

    @OneToMany(mappedBy = "positionuser")
    @Getter
    @Setter
    private List<PositionEntity> positions;

    @Override
    public String toString(){
        return "{" + userName + "}";
    }

}
