package com.example.ohlccryptoapi.persistence.service;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;
import com.example.ohlccryptoapi.domain.service.CryptoCurrencyService;
import com.example.ohlccryptoapi.persistence.JpaEntity.CryptocurrencyJpaEntity;
import com.example.ohlccryptoapi.persistence.repository.jpa.CryptocurrencyJpaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CryptocurrencyJpaEntityService implements  ICURDSERVICE<Cryptocurrency,CryptocurrencyJpaEntity,Long> , CryptoCurrencyService {
 private CryptocurrencyJpaEntityRepository cryptocurrencyJpaEntityRepository;

    @Autowired
    public CryptocurrencyJpaEntityService(CryptocurrencyJpaEntityRepository cryptocurrencyJpaEntityRepository) {
        super();
       this.cryptocurrencyJpaEntityRepository = cryptocurrencyJpaEntityRepository;
   }




    @Override
    public CryptocurrencyJpaEntity transformToEntity(Cryptocurrency domainModel) {
        return new CryptocurrencyJpaEntity(0,"","");
    }

    @Override
    public Cryptocurrency transformToDomainModel(CryptocurrencyJpaEntity Entity) {
           return new Cryptocurrency(0,"","");
    }

    @Override
    public JpaRepository<CryptocurrencyJpaEntity, Long> getRepository() {
        return cryptocurrencyJpaEntityRepository;
    }

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyById(long id) {
        return cryptocurrencyJpaEntityRepository.findById(id).map(entity->transformToDomainModel(entity) );
    }

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyByName(String name) {
        return cryptocurrencyJpaEntityRepository.findByName(name).map(entity->transformToDomainModel(entity));
    }

    @Override
    public Cryptocurrency saveCryptoCurrency(Cryptocurrency cryptocurrency) {
        return saveDomainModel(cryptocurrency);
    }

    @Override
    public List<Cryptocurrency> findAllCryptoCurrency() {
        return getAllDomainModel();
    }

    @Override
    public void deleteCryptocurrency(Cryptocurrency cryptocurrency) {

    }
}
