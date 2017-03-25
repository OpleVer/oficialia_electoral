package com.oplever.sioe.repository;

import com.oplever.sioe.domain.Peticion;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Peticion entity.
 */
@SuppressWarnings("unused")
public interface PeticionRepository extends MongoRepository<Peticion,String> {

}
