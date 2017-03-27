package com.oplever.sioe.service.impl;

import com.oplever.sioe.service.No_presenteService;
import com.oplever.sioe.domain.No_presente;
import com.oplever.sioe.repository.No_presenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing No_presente.
 */
@Service
public class No_presenteServiceImpl implements No_presenteService{

    private final Logger log = LoggerFactory.getLogger(No_presenteServiceImpl.class);
    
    private final No_presenteRepository no_presenteRepository;

    public No_presenteServiceImpl(No_presenteRepository no_presenteRepository) {
        this.no_presenteRepository = no_presenteRepository;
    }

    /**
     * Save a no_presente.
     *
     * @param no_presente the entity to save
     * @return the persisted entity
     */
    @Override
    public No_presente save(No_presente no_presente) {
        log.debug("Request to save No_presente : {}", no_presente);
        No_presente result = no_presenteRepository.save(no_presente);
        return result;
    }

    /**
     *  Get all the no_presentes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<No_presente> findAll(Pageable pageable) {
        log.debug("Request to get all No_presentes");
        Page<No_presente> result = no_presenteRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one no_presente by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public No_presente findOne(String id) {
        log.debug("Request to get No_presente : {}", id);
        No_presente no_presente = no_presenteRepository.findOne(id);
        return no_presente;
    }

    /**
     *  Delete the  no_presente by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete No_presente : {}", id);
        no_presenteRepository.delete(id);
    }
}
