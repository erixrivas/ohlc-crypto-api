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
public class ExchangeCompanyJpaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;

    public ExchangeCompanyJpaEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
