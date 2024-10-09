package org.rabbitx.rabbitbetest.repository.wallet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Column
    @Getter
    @Setter
    private double amount;

    @Column
    @Getter
    @Setter
    private String type;

    @Column(name = "isreverted")
    @Getter
    @Setter
    private boolean isReverted;

    @Column(name = "dateoftransaction")
    @Getter
    @Setter
    @Temporal(value = TemporalType.DATE)
    private LocalDate dateOfTransaction;

    @ManyToOne
    @JoinColumn(name = "transactionid" ,nullable = false)
    @Setter
    private WalletEntity wallet;

    @Override
    public String toString(){
        return "{" + id + "}";
    }

}
