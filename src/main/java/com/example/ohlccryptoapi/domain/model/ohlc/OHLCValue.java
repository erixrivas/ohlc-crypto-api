package com.example.ohlccryptoapi.domain.model.ohlc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OHLCValue {
    private Integer id;
    private Long unixTimestamp;
    private LocalDateTime date;
    private Cryptocurrency cryptocurrency;
    private ExchangeCompany exchangeCompany;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;

    public OHLCValue(Long unixTimestamp, LocalDateTime date, Cryptocurrency cryptocurrency, ExchangeCompany exchangeCompany, Double open, Double high, Double low, Double close, Double volume) {
        this.unixTimestamp = unixTimestamp;
       this.date = date;
        this.cryptocurrency = cryptocurrency;
        this.exchangeCompany = exchangeCompany;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}