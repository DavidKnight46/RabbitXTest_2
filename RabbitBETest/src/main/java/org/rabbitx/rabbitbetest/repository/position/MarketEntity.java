package org.rabbitx.rabbitbetest.repository.position;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "market")
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Column(name = "marketname")
    @Getter
    @Setter
    private String marketName;

    @Column(name = "currentmarketprice")
    @Getter
    @Setter
    private double currentMarketPrice;

    @Column(name = "entrymarketprice")
    @Getter
    @Setter
    private double entryMarketPrice;

    @OneToOne
    @JoinColumn(name = "marketid", nullable = false)
    @Setter
    private PositionEntity position;

    @Override
    public String toString(){
        return "{" + marketName + "}";
    }
}
