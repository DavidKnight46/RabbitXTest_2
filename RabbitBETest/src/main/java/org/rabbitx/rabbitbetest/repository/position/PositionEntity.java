package org.rabbitx.rabbitbetest.repository.position;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;

@Entity
@Table(name = "positions")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Column(name = "sizeofposition")
    @Getter
    @Setter
    private double sizeOfPosition;

    @Column(name = "typeofposition")
    @Getter
    @Setter
    private TypeOfPosition typeOfPosition;

    @Column(name = "isexecuted")
    @Getter
    @Setter
    private boolean isExecuted;

    @Column
    @Getter
    @Setter
    private int leverage;

    @Column(name = "marketid")
    @Getter
    @Setter
    private int marketId;

    @OneToOne(mappedBy = "position")
    @Getter
    @Setter
    private MarketEntity market;

    @ManyToOne
    @JoinColumn(name = "positionid", nullable = false)
    //@Getter
    @Setter
    private UserEntity positionuser;

    @Override
    public String toString(){
        return "{" + id + "}";
    }

}
