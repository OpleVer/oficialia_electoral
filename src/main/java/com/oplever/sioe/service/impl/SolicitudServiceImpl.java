package com.oplever.sioe.service.impl;

import com.oplever.sioe.service.SolicitudService;
import com.oplever.sioe.domain.Solicitud;
import com.oplever.sioe.repository.SolicitudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Solicitud.
 */
@Service
public class SolicitudServiceImpl implements SolicitudService{

    private final Logger log = LoggerFactory.getLogger(SolicitudServiceImpl.class);
    
    private final SolicitudRepository solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    /**
     * Save a solicitud.
     *
     * @param solicitud the entity to save
     * @return the persisted entity
     */
    @Override
    public Solicitud save(Solicitud solicitud) {
        log.debug("Request to save Solicitud : {}", solicitud);
        Solicitud result = solicitudRepository.save(solicitud);
        return result;
    }

    /**
     *  Get all the solicituds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Solicitud> findAll(Pageable pageable) {
        log.debug("Request to get all Solicituds");
        Page<Solicitud> result = solicitudRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one solicitud by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Solicitud findOne(String id) {
        log.debug("Request to get Solicitud : {}", id);
        Solicitud solicitud = solicitudRepository.findOne(id);
        return solicitud;
    }

    /**
     *  Delete the  solicitud by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Solicitud : {}", id);
        solicitudRepository.delete(id);
    }
}
