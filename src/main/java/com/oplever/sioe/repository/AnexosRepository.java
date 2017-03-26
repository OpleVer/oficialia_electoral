package com.oplever.sioe.repository;

import com.oplever.sioe.domain.Anexos;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Anexos entity.
 */
@SuppressWarnings("unused")
public interface AnexosRepository extends MongoRepository<Anexos,String> {

}
