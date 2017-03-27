package com.oplever.sioe.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oplever.sioe.domain.No_presente;
import com.oplever.sioe.service.No_presenteService;
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
 * REST controller for managing No_presente.
 */
@RestController
@RequestMapping("/api")
public class No_presenteResource {

    private final Logger log = LoggerFactory.getLogger(No_presenteResource.class);

    private static final String ENTITY_NAME = "no_presente";
        
    private final No_presenteService no_presenteService;

    public No_presenteResource(No_presenteService no_presenteService) {
        this.no_presenteService = no_presenteService;
    }

    /**
     * POST  /no-presentes : Create a new no_presente.
     *
     * @param no_presente the no_presente to create
     * @return the ResponseEntity with status 201 (Created) and with body the new no_presente, or with status 400 (Bad Request) if the no_presente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/no-presentes")
    @Timed
    public ResponseEntity<No_presente> createNo_presente(@RequestBody No_presente no_presente) throws URISyntaxException {
        log.debug("REST request to save No_presente : {}", no_presente);
        if (no_presente.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new no_presente cannot already have an ID")).body(null);
        }
        No_presente result = no_presenteService.save(no_presente);
        return ResponseEntity.created(new URI("/api/no-presentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /no-presentes : Updates an existing no_presente.
     *
     * @param no_presente the no_presente to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated no_presente,
     * or with status 400 (Bad Request) if the no_presente is not valid,
     * or with status 500 (Internal Server Error) if the no_presente couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/no-presentes")
    @Timed
    public ResponseEntity<No_presente> updateNo_presente(@RequestBody No_presente no_presente) throws URISyntaxException {
        log.debug("REST request to update No_presente : {}", no_presente);
        if (no_presente.getId() == null) {
            return createNo_presente(no_presente);
        }
        No_presente result = no_presenteService.save(no_presente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, no_presente.getId().toString()))
            .body(result);
    }

    /**
     * GET  /no-presentes : get all the no_presentes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of no_presentes in body
     */
    @GetMapping("/no-presentes")
    @Timed
    public ResponseEntity<List<No_presente>> getAllNo_presentes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of No_presentes");
        Page<No_presente> page = no_presenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/no-presentes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /no-presentes/:id : get the "id" no_presente.
     *
     * @param id the id of the no_presente to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the no_presente, or with status 404 (Not Found)
     */
    @GetMapping("/no-presentes/{id}")
    @Timed
    public ResponseEntity<No_presente> getNo_presente(@PathVariable String id) {
        log.debug("REST request to get No_presente : {}", id);
        No_presente no_presente = no_presenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(no_presente));
    }

    /**
     * DELETE  /no-presentes/:id : delete the "id" no_presente.
     *
     * @param id the id of the no_presente to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/no-presentes/{id}")
    @Timed
    public ResponseEntity<Void> deleteNo_presente(@PathVariable String id) {
        log.debug("REST request to delete No_presente : {}", id);
        no_presenteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
