package com.oplever.sioe.repository;

import com.oplever.sioe.domain.Origen;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Origen entity.
 */
@SuppressWarnings("unused")
public interface OrigenRepository extends MongoRepository<Origen,String> {

}
