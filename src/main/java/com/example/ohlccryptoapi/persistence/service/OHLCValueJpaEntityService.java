package com.example.ohlccryptoapi.persistence.service;

import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;
import com.example.ohlccryptoapi.domain.service.ohlc.OHLCValueService;
import com.example.ohlccryptoapi.persistence.JpaEntity.CryptocurrencyJpaEntity;
import com.example.ohlccryptoapi.persistence.JpaEntity.OHLCValueJpaEntity;
import com.example.ohlccryptoapi.persistence.repository.jpa.OHLCValueJpaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class OHLCValueJpaEntityService  implements ICURDSERVICE<OHLCValue, OHLCValueJpaEntity,Integer> , OHLCValueService {
    private OHLCValueJpaEntityRepository ohlcValueJpaEntityRepository;
    private CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService;
    private ExchangeCompanyJpaEntityService exchangeCompanyJpaEntityService;
    @Autowired
    public OHLCValueJpaEntityService(OHLCValueJpaEntityRepository ohlcValueJpaEntityRepository, CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService, ExchangeCompanyJpaEntityService exchangeCompanyJpaEntityService) {
       super();
        this.ohlcValueJpaEntityRepository = ohlcValueJpaEntityRepository;
        this.cryptocurrencyJpaEntityService = cryptocurrencyJpaEntityService;
        this.exchangeCompanyJpaEntityService = exchangeCompanyJpaEntityService;
    }

    @Override
    public Optional<OHLCValue> findOHLCValueById(Integer id) {
        return ohlcValueJpaEntityRepository.findById(id).map(entity->transformToDomainModel(entity) );
    }

    @Override
    public List<OHLCValue> findOHLCValueByUnixTimestamp(Long unixTimestamp) {
        return ohlcValueJpaEntityRepository.findByUnixTimestamp(unixTimestamp).stream().map(entity->transformToDomainModel(entity)).collect(Collectors.toList());
    }

    @Override
    public List<OHLCValue> findAllByDateOHLCValue(LocalDateTime start, LocalDateTime end) {
        return ohlcValueJpaEntityRepository.findAllByDateBetween (start,end).stream().map(entity->transformToDomainModel(entity)).collect(Collectors.toList());
    }

    @Override
    public OHLCValue saveOHLCValue(OHLCValue OHLCValue) {
        return saveDomainModel(OHLCValue);
    }

    @Override
    public List<OHLCValue> findAllOHLCValue() {
           return getAllDomainModel();
    }

    @Override
    public void deleteOHLCValue(OHLCValue OHLCValue) {
        ohlcValueJpaEntityRepository.delete(transformToEntity(OHLCValue));
    }

    @Override
    public OHLCValueJpaEntity transformToEntity(OHLCValue domainModel) {
        if (domainModel!=null) {
            if (domainModel.getId()!=null)
                return new OHLCValueJpaEntity(domainModel.getId(), domainModel.getUnixTimestamp(), domainModel.getDate(),
                        cryptocurrencyJpaEntityService.transformToEntity( domainModel.getCryptocurrency()),
                        exchangeCompanyJpaEntityService.transformToEntity( domainModel.getExchangeCompany()),
                        domainModel.getOpen(),domainModel.getHigh(),domainModel.getLow(),domainModel.getClose(),domainModel.getVolume());
            else
                return new OHLCValueJpaEntity( domainModel.getUnixTimestamp(), domainModel.getDate(),
                        cryptocurrencyJpaEntityService.transformToEntity( domainModel.getCryptocurrency()),
                        exchangeCompanyJpaEntityService.transformToEntity( domainModel.getExchangeCompany()),
                        domainModel.getOpen(),domainModel.getHigh(),domainModel.getLow(),domainModel.getClose(),domainModel.getVolume());
        }
        else return null;
    }

    @Override
    public OHLCValue transformToDomainModel(OHLCValueJpaEntity entity) {
        if (entity!=null) {
            if (entity.getId()!=null)
                return new OHLCValue(entity.getId(), entity.getUnixTimestamp(), entity.getDate(),
                      cryptocurrencyJpaEntityService.transformToDomainModel( entity.getCryptocurrencyJpaEntity()),
                       exchangeCompanyJpaEntityService.transformToDomainModel( entity.getExchangeCompanyJpaEntity()),
                        entity.getOpen(),entity.getHigh(),entity.getLow(),entity.getClose(),entity.getVolume());
            else
                return new OHLCValue( entity.getUnixTimestamp(), entity.getDate(),
                        cryptocurrencyJpaEntityService.transformToDomainModel( entity.getCryptocurrencyJpaEntity()),
                        exchangeCompanyJpaEntityService.transformToDomainModel( entity.getExchangeCompanyJpaEntity()),
                        entity.getOpen(),entity.getHigh(),entity.getLow(),entity.getClose(),entity.getVolume());
        }
        else return null;
    }

    @Override
    public JpaRepository<OHLCValueJpaEntity, Integer> getRepository() {
        return ohlcValueJpaEntityRepository;
    }
}
