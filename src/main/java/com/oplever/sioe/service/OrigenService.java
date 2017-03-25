package com.oplever.sioe.service;

import com.oplever.sioe.domain.Origen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Origen.
 */
public interface OrigenService {

    /**
     * Save a origen.
     *
     * @param origen the entity to save
     * @return the persisted entity
     */
    Origen save(Origen origen);

    /**
     *  Get all the origens.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Origen> findAll(Pageable pageable);

    /**
     *  Get the "id" origen.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Origen findOne(String id);

    /**
     *  Delete the "id" origen.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
