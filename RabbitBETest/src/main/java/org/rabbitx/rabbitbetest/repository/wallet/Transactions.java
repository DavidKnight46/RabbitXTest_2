package org.rabbitx.rabbitbetest.repository.wallet;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double amount;

    @Column
    private String type;

    @Column(name = "isreverted")
    private boolean isReverted;

    @Column(name = "")
    @Temporal(value = TemporalType.DATE)
    private LocalDate dateOfTransaction;


}
