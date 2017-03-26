package com.oplever.sioe.service;

import com.oplever.sioe.domain.Anexos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Anexos.
 */
public interface AnexosService {

    /**
     * Save a anexos.
     *
     * @param anexos the entity to save
     * @return the persisted entity
     */
    Anexos save(Anexos anexos);

    /**
     *  Get all the anexos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Anexos> findAll(Pageable pageable);

    /**
     *  Get the "id" anexos.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Anexos findOne(String id);

    /**
     *  Delete the "id" anexos.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
