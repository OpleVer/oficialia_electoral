package com.oplever.sioe.repository;

import com.oplever.sioe.domain.Procede;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Procede entity.
 */
@SuppressWarnings("unused")
public interface ProcedeRepository extends MongoRepository<Procede,String> {

}
