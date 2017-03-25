package com.oplever.sioe.service;

import com.oplever.sioe.domain.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Solicitud.
 */
public interface SolicitudService {

    /**
     * Save a solicitud.
     *
     * @param solicitud the entity to save
     * @return the persisted entity
     */
    Solicitud save(Solicitud solicitud);

    /**
     *  Get all the solicituds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Solicitud> findAll(Pageable pageable);

    /**
     *  Get the "id" solicitud.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Solicitud findOne(String id);

    /**
     *  Delete the "id" solicitud.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
