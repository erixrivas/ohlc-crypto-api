package com.example.ohlccryptoapi.infrastructure.persistence.JpaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class CryptocurrencyJpaEntity {
    @Id
    private long id;
    @Column
    private String name;
    @Column
    private String Symbol;


}
