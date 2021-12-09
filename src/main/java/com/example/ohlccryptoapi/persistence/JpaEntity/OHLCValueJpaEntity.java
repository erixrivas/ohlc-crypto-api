package com.example.ohlccryptoapi.persistence.JpaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class OHLCValueJpaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Long unixTimestamp;
    @Column
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn
    private CryptocurrencyJpaEntity cryptocurrencyJpaEntity;
    @ManyToOne
    @JoinColumn
    private ExchangeCompanyJpaEntity exchangeCompanyJpaEntity;
    @Column
    private Double open;
    @Column
    private Double high;
    @Column
    private Double Low;
    @Column
    private Double close;
    @Column
    private Double volume;

    public OHLCValueJpaEntity(Long unixTimestamp, LocalDateTime date, CryptocurrencyJpaEntity cryptocurrencyJpaEntity, ExchangeCompanyJpaEntity exchangeCompanyJpaEntity, Double open, Double high, Double low, Double close, Double volume) {
        this.unixTimestamp = unixTimestamp;
        this.date = date;
        this.cryptocurrencyJpaEntity = cryptocurrencyJpaEntity;
        this.exchangeCompanyJpaEntity = exchangeCompanyJpaEntity;
        this.open = open;
        this.high = high;
        Low = low;
        this.close = close;
        this.volume = volume;
    }
}