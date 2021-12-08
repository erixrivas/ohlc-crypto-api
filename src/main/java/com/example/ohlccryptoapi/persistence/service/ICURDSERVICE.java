package com.example.ohlccryptoapi.infrastructure.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface ICURDSERVICE<D,T,ID> {

	public default D saveDomainModel(D domainModel) {

	return 	transformToDomainModel(getRepository().save( transformToEntity(domainModel)));

	}
	public default List<D> getAllDomainModel() {
		return getRepository().findAll().stream().map(entity->transformToDomainModel(entity)).collect(Collectors.toList());

	}
	public T transformToEntity(D domainModel);
	public D transformToDomainModel(T Entity);
	public default T save(T entity) {
	return 	getRepository().save( entity);

	}
	public default List<T> saveAll(Iterable<T> entities) {
		
		return getRepository().saveAll( entities);
	}
	
	public default List<T> getAll() {
		return getRepository().findAll();

	}

	public JpaRepository<T, ID> getRepository();

}