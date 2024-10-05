package org.rabbitx.rabbitbetest.repository.position;

import jakarta.persistence.*;
import lombok.Data;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;

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

    //@Column(name = "market")
    @OneToOne(mappedBy = "id")
    private MarketEntity market;

    @Column(name = "isexecuted")
    boolean isExecuted;

    @Column
    int leverage;
}
