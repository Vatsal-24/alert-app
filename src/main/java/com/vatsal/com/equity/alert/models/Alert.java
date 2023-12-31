package com.vatsal.com.equity.alert.models;

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
@Table(name = "ALERT")
public class Alert {

    @Id
    @GeneratedValue
    private int id;
    private String stock;
    private double price;
    private String cond;
}
