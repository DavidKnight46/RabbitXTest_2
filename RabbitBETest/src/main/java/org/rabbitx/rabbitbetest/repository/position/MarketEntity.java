package org.rabbitx.rabbitbetest.repository.position;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "market")
@Data
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "marketname")
    private String marketName;

    @Column(name = "currentmarketprice")
    private double currentMarketPrice;

    @Column(name = "entrymarketprice")
    private double entryMarketPrice;

    @OneToOne
    @JoinColumn(name = "marketId", nullable = false)
    private PositionEntity position;
}
