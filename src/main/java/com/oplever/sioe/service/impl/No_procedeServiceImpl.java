package com.oplever.sioe.service.impl;

import com.oplever.sioe.service.No_procedeService;
import com.oplever.sioe.domain.No_procede;
import com.oplever.sioe.repository.No_procedeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing No_procede.
 */
@Service
public class No_procedeServiceImpl implements No_procedeService{

    private final Logger log = LoggerFactory.getLogger(No_procedeServiceImpl.class);
    
    private final No_procedeRepository no_procedeRepository;

    public No_procedeServiceImpl(No_procedeRepository no_procedeRepository) {
        this.no_procedeRepository = no_procedeRepository;
    }

    /**
     * Save a no_procede.
     *
     * @param no_procede the entity to save
     * @return the persisted entity
     */
    @Override
    public No_procede save(No_procede no_procede) {
        log.debug("Request to save No_procede : {}", no_procede);
        No_procede result = no_procedeRepository.save(no_procede);
        return result;
    }

    /**
     *  Get all the no_procedes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<No_procede> findAll(Pageable pageable) {
        log.debug("Request to get all No_procedes");
        Page<No_procede> result = no_procedeRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one no_procede by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public No_procede findOne(String id) {
        log.debug("Request to get No_procede : {}", id);
        No_procede no_procede = no_procedeRepository.findOne(id);
        return no_procede;
    }

    /**
     *  Delete the  no_procede by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete No_procede : {}", id);
        no_procedeRepository.delete(id);
    }
}
