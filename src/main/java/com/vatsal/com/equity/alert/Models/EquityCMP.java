package com.vatsal.com.equity.alert.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EQUITY_CMP")
public class EquityCMP {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double cmp;

    public EquityCMP(String name, double cmp) {
        this.name = name;
        this.cmp = cmp;
    }
}
