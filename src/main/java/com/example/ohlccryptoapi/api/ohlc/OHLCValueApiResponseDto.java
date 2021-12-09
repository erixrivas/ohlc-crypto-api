package com.example.ohlccryptoapi.api.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OHLCValueApiResponseDto {
     Integer id;
     Long unixTimestamp;
     LocalDateTime date;
     GetCryptocurrencyApiResponseDto cryptocurrency;
     ExchangeCompanyApiResponseDto exchangeCompany;
     Double open;
     Double high;
     Double low;
     Double close;
     Double volume;

     public OHLCValueApiResponseDto(Integer id, Long unixTimestamp, LocalDateTime date, Cryptocurrency cryptocurrency, ExchangeCompany exchangeCompany, Double open, Double high, Double low, Double close, Double volume) {
          this.id = id;
          this.unixTimestamp = unixTimestamp;
          this.date = date;
          this.cryptocurrency = new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol());
          this.exchangeCompany =new ExchangeCompanyApiResponseDto( exchangeCompany.getName(),exchangeCompany.getDescription());
          this.open = open;
          this.high = high;
          this.low = low;
          this.close = close;
          this.volume = volume;

     }

}