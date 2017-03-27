package com.oplever.sioe.service.impl;

import com.oplever.sioe.service.ProcedeService;
import com.oplever.sioe.domain.Procede;
import com.oplever.sioe.repository.ProcedeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Procede.
 */
@Service
public class ProcedeServiceImpl implements ProcedeService{

    private final Logger log = LoggerFactory.getLogger(ProcedeServiceImpl.class);
    
    private final ProcedeRepository procedeRepository;

    public ProcedeServiceImpl(ProcedeRepository procedeRepository) {
        this.procedeRepository = procedeRepository;
    }

    /**
     * Save a procede.
     *
     * @param procede the entity to save
     * @return the persisted entity
     */
    @Override
    public Procede save(Procede procede) {
        log.debug("Request to save Procede : {}", procede);
        Procede result = procedeRepository.save(procede);
        return result;
    }

    /**
     *  Get all the procedes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Procede> findAll(Pageable pageable) {
        log.debug("Request to get all Procedes");
        Page<Procede> result = procedeRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one procede by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Procede findOne(String id) {
        log.debug("Request to get Procede : {}", id);
        Procede procede = procedeRepository.findOne(id);
        return procede;
    }

    /**
     *  Delete the  procede by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Procede : {}", id);
        procedeRepository.delete(id);
    }
}
