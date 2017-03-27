package com.oplever.sioe.service;

import com.oplever.sioe.domain.No_procede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing No_procede.
 */
public interface No_procedeService {

    /**
     * Save a no_procede.
     *
     * @param no_procede the entity to save
     * @return the persisted entity
     */
    No_procede save(No_procede no_procede);

    /**
     *  Get all the no_procedes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<No_procede> findAll(Pageable pageable);

    /**
     *  Get the "id" no_procede.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    No_procede findOne(String id);

    /**
     *  Delete the "id" no_procede.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
