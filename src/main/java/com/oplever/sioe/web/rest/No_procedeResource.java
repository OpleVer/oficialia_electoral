package com.oplever.sioe.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oplever.sioe.domain.No_procede;
import com.oplever.sioe.service.No_procedeService;
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
 * REST controller for managing No_procede.
 */
@RestController
@RequestMapping("/api")
public class No_procedeResource {

    private final Logger log = LoggerFactory.getLogger(No_procedeResource.class);

    private static final String ENTITY_NAME = "no_procede";
        
    private final No_procedeService no_procedeService;

    public No_procedeResource(No_procedeService no_procedeService) {
        this.no_procedeService = no_procedeService;
    }

    /**
     * POST  /no-procedes : Create a new no_procede.
     *
     * @param no_procede the no_procede to create
     * @return the ResponseEntity with status 201 (Created) and with body the new no_procede, or with status 400 (Bad Request) if the no_procede has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/no-procedes")
    @Timed
    public ResponseEntity<No_procede> createNo_procede(@RequestBody No_procede no_procede) throws URISyntaxException {
        log.debug("REST request to save No_procede : {}", no_procede);
        if (no_procede.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new no_procede cannot already have an ID")).body(null);
        }
        No_procede result = no_procedeService.save(no_procede);
        return ResponseEntity.created(new URI("/api/no-procedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /no-procedes : Updates an existing no_procede.
     *
     * @param no_procede the no_procede to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated no_procede,
     * or with status 400 (Bad Request) if the no_procede is not valid,
     * or with status 500 (Internal Server Error) if the no_procede couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/no-procedes")
    @Timed
    public ResponseEntity<No_procede> updateNo_procede(@RequestBody No_procede no_procede) throws URISyntaxException {
        log.debug("REST request to update No_procede : {}", no_procede);
        if (no_procede.getId() == null) {
            return createNo_procede(no_procede);
        }
        No_procede result = no_procedeService.save(no_procede);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, no_procede.getId().toString()))
            .body(result);
    }

    /**
     * GET  /no-procedes : get all the no_procedes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of no_procedes in body
     */
    @GetMapping("/no-procedes")
    @Timed
    public ResponseEntity<List<No_procede>> getAllNo_procedes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of No_procedes");
        Page<No_procede> page = no_procedeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/no-procedes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /no-procedes/:id : get the "id" no_procede.
     *
     * @param id the id of the no_procede to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the no_procede, or with status 404 (Not Found)
     */
    @GetMapping("/no-procedes/{id}")
    @Timed
    public ResponseEntity<No_procede> getNo_procede(@PathVariable String id) {
        log.debug("REST request to get No_procede : {}", id);
        No_procede no_procede = no_procedeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(no_procede));
    }

    /**
     * DELETE  /no-procedes/:id : delete the "id" no_procede.
     *
     * @param id the id of the no_procede to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/no-procedes/{id}")
    @Timed
    public ResponseEntity<Void> deleteNo_procede(@PathVariable String id) {
        log.debug("REST request to delete No_procede : {}", id);
        no_procedeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
