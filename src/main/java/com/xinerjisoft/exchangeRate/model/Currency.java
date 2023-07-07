package com.xinerjisoft.exchangeRate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private Date date;


    @Column(name = "currencyCode", nullable = false)
    private String currencyCode;

    @Column(name = "kod", nullable = false)
    private String kod;

    @Column(name = "banknoteSelling")
    private double banknoteSelling;

    @Column(name = "unit")
    private int unit;

    @Column(name = "crossOrder")
    private int crossOrder;

    @Column(name = "forexSelling")
    private double forexSelling;

    @Column(name = "forexBuying")
    private double forexBuying;

    @Column(name = "banknoteBuying")
    private double banknoteBuying;

    @Column(name = "crossRateOther")
    private double crossRateOther;

    @Column(name = "crossRateUSD")
    private double crossRateUSD;

}
