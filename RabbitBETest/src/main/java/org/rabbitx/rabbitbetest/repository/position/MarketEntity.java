package org.rabbitx.rabbitbetest.repository.position;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "market")
@Data
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "marketname")
    private String marketName;

    @OneToOne
    @JoinColumn(name = "marketId", nullable = false)
    private PositionEntity position;
}
