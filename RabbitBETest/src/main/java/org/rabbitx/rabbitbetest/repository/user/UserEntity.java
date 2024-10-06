package org.rabbitx.rabbitbetest.repository.user;

import jakarta.persistence.*;
import lombok.Data;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column
    private long walletID;

    @Column
    private long positionId;

    @OneToMany(mappedBy = "walletUser")
    private List<WalletEntity> wallets;

    @OneToMany(mappedBy = "positionuser")
    private ArrayList<PositionEntity> positions;

}
