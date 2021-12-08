package com.example.ohlccryptoapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCryptocurrencyApiResponseDto {
    String name;
    String Symbol;
}
