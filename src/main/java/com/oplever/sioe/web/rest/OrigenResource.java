package com.oplever.sioe.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oplever.sioe.domain.Origen;
import com.oplever.sioe.service.OrigenService;
import com.oplever.sioe.web.rest.util.HeaderUtil;
import com.oplever.sioe.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Origen.
 */
@RestController
@RequestMapping("/api")
public class OrigenResource {

    private final Logger log = LoggerFactory.getLogger(OrigenResource.class);

    private static final String ENTITY_NAME = "origen";
        
    private final OrigenService origenService;

    public OrigenResource(OrigenService origenService) {
        this.origenService = origenService;
    }

    /**
     * POST  /origens : Create a new origen.
     *
     * @param origen the origen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new origen, or with status 400 (Bad Request) if the origen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/origens")
    @Timed
    public ResponseEntity<Origen> createOrigen(@RequestBody Origen origen) throws URISyntaxException {
        log.debug("REST request to save Origen : {}", origen);
        if (origen.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new origen cannot already have an ID")).body(null);
        }
        Origen result = origenService.save(origen);
        return ResponseEntity.created(new URI("/api/origens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /origens : Updates an existing origen.
     *
     * @param origen the origen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated origen,
     * or with status 400 (Bad Request) if the origen is not valid,
     * or with status 500 (Internal Server Error) if the origen couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/origens")
    @Timed
    public ResponseEntity<Origen> updateOrigen(@RequestBody Origen origen) throws URISyntaxException {
        log.debug("REST request to update Origen : {}", origen);
        if (origen.getId() == null) {
            return createOrigen(origen);
        }
        Origen result = origenService.save(origen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, origen.getId().toString()))
            .body(result);
    }

    /**
     * GET  /origens : get all the origens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of origens in body
     */
    @GetMapping("/origens")
    @Timed
    public ResponseEntity<List<Origen>> getAllOrigens(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Origens");
        Page<Origen> page = origenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/origens");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /origens/:id : get the "id" origen.
     *
     * @param id the id of the origen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the origen, or with status 404 (Not Found)
     */
    @GetMapping("/origens/{id}")
    @Timed
    public ResponseEntity<Origen> getOrigen(@PathVariable String id) {
        log.debug("REST request to get Origen : {}", id);
        Origen origen = origenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(origen));
    }

    /**
     * DELETE  /origens/:id : delete the "id" origen.
     *
     * @param id the id of the origen to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/origens/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrigen(@PathVariable String id) {
        log.debug("REST request to delete Origen : {}", id);
        origenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
