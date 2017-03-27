package com.oplever.sioe.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oplever.sioe.domain.Procede;
import com.oplever.sioe.service.ProcedeService;
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
 * REST controller for managing Procede.
 */
@RestController
@RequestMapping("/api")
public class ProcedeResource {

    private final Logger log = LoggerFactory.getLogger(ProcedeResource.class);

    private static final String ENTITY_NAME = "procede";
        
    private final ProcedeService procedeService;

    public ProcedeResource(ProcedeService procedeService) {
        this.procedeService = procedeService;
    }

    /**
     * POST  /procedes : Create a new procede.
     *
     * @param procede the procede to create
     * @return the ResponseEntity with status 201 (Created) and with body the new procede, or with status 400 (Bad Request) if the procede has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/procedes")
    @Timed
    public ResponseEntity<Procede> createProcede(@RequestBody Procede procede) throws URISyntaxException {
        log.debug("REST request to save Procede : {}", procede);
        if (procede.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new procede cannot already have an ID")).body(null);
        }
        Procede result = procedeService.save(procede);
        return ResponseEntity.created(new URI("/api/procedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /procedes : Updates an existing procede.
     *
     * @param procede the procede to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated procede,
     * or with status 400 (Bad Request) if the procede is not valid,
     * or with status 500 (Internal Server Error) if the procede couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/procedes")
    @Timed
    public ResponseEntity<Procede> updateProcede(@RequestBody Procede procede) throws URISyntaxException {
        log.debug("REST request to update Procede : {}", procede);
        if (procede.getId() == null) {
            return createProcede(procede);
        }
        Procede result = procedeService.save(procede);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, procede.getId().toString()))
            .body(result);
    }

    /**
     * GET  /procedes : get all the procedes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of procedes in body
     */
    @GetMapping("/procedes")
    @Timed
    public ResponseEntity<List<Procede>> getAllProcedes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Procedes");
        Page<Procede> page = procedeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/procedes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /procedes/:id : get the "id" procede.
     *
     * @param id the id of the procede to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the procede, or with status 404 (Not Found)
     */
    @GetMapping("/procedes/{id}")
    @Timed
    public ResponseEntity<Procede> getProcede(@PathVariable String id) {
        log.debug("REST request to get Procede : {}", id);
        Procede procede = procedeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(procede));
    }

    /**
     * DELETE  /procedes/:id : delete the "id" procede.
     *
     * @param id the id of the procede to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/procedes/{id}")
    @Timed
    public ResponseEntity<Void> deleteProcede(@PathVariable String id) {
        log.debug("REST request to delete Procede : {}", id);
        procedeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
