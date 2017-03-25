package com.oplever.sioe.service.impl;

import com.oplever.sioe.service.OrigenService;
import com.oplever.sioe.domain.Origen;
import com.oplever.sioe.repository.OrigenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Origen.
 */
@Service
public class OrigenServiceImpl implements OrigenService{

    private final Logger log = LoggerFactory.getLogger(OrigenServiceImpl.class);
    
    private final OrigenRepository origenRepository;

    public OrigenServiceImpl(OrigenRepository origenRepository) {
        this.origenRepository = origenRepository;
    }

    /**
     * Save a origen.
     *
     * @param origen the entity to save
     * @return the persisted entity
     */
    @Override
    public Origen save(Origen origen) {
        log.debug("Request to save Origen : {}", origen);
        Origen result = origenRepository.save(origen);
        return result;
    }

    /**
     *  Get all the origens.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Origen> findAll(Pageable pageable) {
        log.debug("Request to get all Origens");
        Page<Origen> result = origenRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one origen by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Origen findOne(String id) {
        log.debug("Request to get Origen : {}", id);
        Origen origen = origenRepository.findOne(id);
        return origen;
    }

    /**
     *  Delete the  origen by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Origen : {}", id);
        origenRepository.delete(id);
    }
}
