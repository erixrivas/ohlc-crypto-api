package com.example.ohlccryptoapi.infrastructure.persistence.JpaEntity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class CryptocurrencyJpaEntity {
    @Id
    private long id;
    @Column
    private String name;
    @Column
    private String Symbol;
}
