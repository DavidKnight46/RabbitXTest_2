package org.rabbitx.rabbitbetest.repository.position;

import jakarta.persistence.*;
import lombok.Data;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;

@Entity
@Table(name = "positions")
@Data
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sizeofposition")
    private double sizeOfPosition;

    @Column(name = "typeofposition")
    private TypeOfPosition typeOfPosition;

    @Column(name = "isexecuted")
    boolean isExecuted;

    @Column
    int leverage;

    @Column(name = "marketid")
    long marketId;

    @OneToOne(mappedBy = "position")
    MarketEntity market;

    @ManyToOne
    @JoinColumn(name = "positionId")
    private UserEntity positionuser;

}
