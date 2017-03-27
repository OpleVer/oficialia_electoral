package com.oplever.sioe.service;

import com.oplever.sioe.domain.No_presente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing No_presente.
 */
public interface No_presenteService {

    /**
     * Save a no_presente.
     *
     * @param no_presente the entity to save
     * @return the persisted entity
     */
    No_presente save(No_presente no_presente);

    /**
     *  Get all the no_presentes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<No_presente> findAll(Pageable pageable);

    /**
     *  Get the "id" no_presente.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    No_presente findOne(String id);

    /**
     *  Delete the "id" no_presente.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
