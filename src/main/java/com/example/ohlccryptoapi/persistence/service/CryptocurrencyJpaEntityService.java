package com.example.ohlccryptoapi.persistence.service;

import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.service.ohlc.CryptoCurrencyService;
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
public class CryptocurrencyJpaEntityService implements  ICURDSERVICE<Cryptocurrency,CryptocurrencyJpaEntity,Integer> , CryptoCurrencyService {
 private CryptocurrencyJpaEntityRepository cryptocurrencyJpaEntityRepository;

    @Autowired
    public CryptocurrencyJpaEntityService(CryptocurrencyJpaEntityRepository cryptocurrencyJpaEntityRepository) {
        super();
       this.cryptocurrencyJpaEntityRepository = cryptocurrencyJpaEntityRepository;
   }




    @Override
    public CryptocurrencyJpaEntity transformToEntity(Cryptocurrency domainModel) {
        if (domainModel!=null){
            if(domainModel.getId()!=null)
            return new CryptocurrencyJpaEntity(domainModel.getId() , domainModel.getName(), domainModel.getSymbol());
            else return new CryptocurrencyJpaEntity(domainModel.getName(), domainModel.getSymbol());
        }
        else return  null;


    }

    @Override
    public Cryptocurrency transformToDomainModel(CryptocurrencyJpaEntity entity) {
        if (entity!=null) {
            if (entity.getId()!=null)
            return new Cryptocurrency(entity.getId(), entity.getName(), entity.getSymbol());
            else
                return new Cryptocurrency(entity.getName(), entity.getSymbol());
        }
        else return null;
    }

    @Override
    public JpaRepository<CryptocurrencyJpaEntity, Integer> getRepository() {
        return cryptocurrencyJpaEntityRepository;
    }

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyById(Integer id) {
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
        cryptocurrencyJpaEntityRepository.delete(transformToEntity(cryptocurrency));
    }
}
