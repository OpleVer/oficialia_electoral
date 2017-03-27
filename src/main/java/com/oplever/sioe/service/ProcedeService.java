package com.oplever.sioe.service;

import com.oplever.sioe.domain.Procede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Procede.
 */
public interface ProcedeService {

    /**
     * Save a procede.
     *
     * @param procede the entity to save
     * @return the persisted entity
     */
    Procede save(Procede procede);

    /**
     *  Get all the procedes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Procede> findAll(Pageable pageable);

    /**
     *  Get the "id" procede.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Procede findOne(String id);

    /**
     *  Delete the "id" procede.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
