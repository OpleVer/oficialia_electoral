package com.oplever.sioe.repository;

import com.oplever.sioe.domain.Peticionario;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Peticionario entity.
 */
@SuppressWarnings("unused")
public interface PeticionarioRepository extends MongoRepository<Peticionario,String> {

}
