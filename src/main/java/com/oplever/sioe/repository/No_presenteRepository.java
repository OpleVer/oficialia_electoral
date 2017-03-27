package com.oplever.sioe.repository;

import com.oplever.sioe.domain.No_presente;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the No_presente entity.
 */
@SuppressWarnings("unused")
public interface No_presenteRepository extends MongoRepository<No_presente,String> {

}
