package com.example.ohlccryptoapi.persistence.service;

import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import com.example.ohlccryptoapi.domain.service.ohlc.ExchangeCompanyService;
import com.example.ohlccryptoapi.persistence.JpaEntity.ExchangeCompanyJpaEntity;
import com.example.ohlccryptoapi.persistence.repository.jpa.ExchangeCompanyJpaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExchangeCompanyJpaEntityService implements  ICURDSERVICE<ExchangeCompany,ExchangeCompanyJpaEntity,Integer> , ExchangeCompanyService {
    private ExchangeCompanyJpaEntityRepository ExchangeCompanyJpaEntityRepository;

    @Autowired
    public ExchangeCompanyJpaEntityService(ExchangeCompanyJpaEntityRepository ExchangeCompanyJpaEntityRepository) {
        super();
        this.ExchangeCompanyJpaEntityRepository = ExchangeCompanyJpaEntityRepository;
    }




    @Override
    public ExchangeCompanyJpaEntity transformToEntity(ExchangeCompany domainModel) {
        if (domainModel!=null){
            if(domainModel.getId()!=null)
                return new ExchangeCompanyJpaEntity(domainModel.getId() , domainModel.getName(), domainModel.getDescription());
            else return new ExchangeCompanyJpaEntity(domainModel.getName(), domainModel.getDescription());
        }
        else return  null;


    }

    @Override
    public ExchangeCompany transformToDomainModel(ExchangeCompanyJpaEntity entity) {
        if (entity!=null) {
            if (entity.getId()!=null)
                return new ExchangeCompany(entity.getId(), entity.getName(), entity.getDescription());
            else
                return new ExchangeCompany(entity.getName(), entity.getDescription());
        }
        else return null;
    }

    @Override
    public JpaRepository<ExchangeCompanyJpaEntity, Integer> getRepository() {
        return ExchangeCompanyJpaEntityRepository;
    }

    @Override
    public Optional<ExchangeCompany> findExchangeCompanyById(Integer id) {
        return ExchangeCompanyJpaEntityRepository.findById(id).map(entity->transformToDomainModel(entity) );
    }

    @Override
    public Optional<ExchangeCompany> findExchangeCompanyByName(String name) {
        return ExchangeCompanyJpaEntityRepository.findByName(name).map(entity->transformToDomainModel(entity));
    }

    @Override
    public ExchangeCompany saveExchangeCompany(ExchangeCompany ExchangeCompany) {
        return saveDomainModel(ExchangeCompany);
    }

    @Override
    public List<ExchangeCompany> findAllExchangeCompany() {
        return getAllDomainModel();
    }

    @Override
    public void deleteExchangeCompany(ExchangeCompany ExchangeCompany) {
        ExchangeCompanyJpaEntityRepository.delete(transformToEntity(ExchangeCompany));
    }
}
