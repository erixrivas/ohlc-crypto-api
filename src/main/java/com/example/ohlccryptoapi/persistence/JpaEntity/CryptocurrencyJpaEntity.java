package com.example.ohlccryptoapi.persistence.JpaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class CryptocurrencyJpaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String Symbol;

    public CryptocurrencyJpaEntity(String name, String symbol) {
        this.name = name;
        Symbol = symbol;
    }
}
