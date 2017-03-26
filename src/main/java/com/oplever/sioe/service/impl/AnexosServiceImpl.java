package com.oplever.sioe.service.impl;

import com.oplever.sioe.service.AnexosService;
import com.oplever.sioe.domain.Anexos;
import com.oplever.sioe.repository.AnexosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Anexos.
 */
@Service
public class AnexosServiceImpl implements AnexosService{

    private final Logger log = LoggerFactory.getLogger(AnexosServiceImpl.class);
    
    private final AnexosRepository anexosRepository;

    public AnexosServiceImpl(AnexosRepository anexosRepository) {
        this.anexosRepository = anexosRepository;
    }

    /**
     * Save a anexos.
     *
     * @param anexos the entity to save
     * @return the persisted entity
     */
    @Override
    public Anexos save(Anexos anexos) {
        log.debug("Request to save Anexos : {}", anexos);
        Anexos result = anexosRepository.save(anexos);
        return result;
    }

    /**
     *  Get all the anexos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Anexos> findAll(Pageable pageable) {
        log.debug("Request to get all Anexos");
        Page<Anexos> result = anexosRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one anexos by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Anexos findOne(String id) {
        log.debug("Request to get Anexos : {}", id);
        Anexos anexos = anexosRepository.findOne(id);
        return anexos;
    }

    /**
     *  Delete the  anexos by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Anexos : {}", id);
        anexosRepository.delete(id);
    }
}
