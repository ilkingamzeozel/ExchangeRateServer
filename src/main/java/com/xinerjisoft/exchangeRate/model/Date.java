package com.xinerjisoft.exchangeRate.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private String date;

}