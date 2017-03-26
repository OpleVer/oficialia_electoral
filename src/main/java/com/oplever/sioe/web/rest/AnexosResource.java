package com.oplever.sioe.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oplever.sioe.domain.Anexos;
import com.oplever.sioe.service.AnexosService;
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
 * REST controller for managing Anexos.
 */
@RestController
@RequestMapping("/api")
public class AnexosResource {

    private final Logger log = LoggerFactory.getLogger(AnexosResource.class);

    private static final String ENTITY_NAME = "anexos";
        
    private final AnexosService anexosService;

    public AnexosResource(AnexosService anexosService) {
        this.anexosService = anexosService;
    }

    /**
     * POST  /anexos : Create a new anexos.
     *
     * @param anexos the anexos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new anexos, or with status 400 (Bad Request) if the anexos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/anexos")
    @Timed
    public ResponseEntity<Anexos> createAnexos(@RequestBody Anexos anexos) throws URISyntaxException {
        log.debug("REST request to save Anexos : {}", anexos);
        if (anexos.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new anexos cannot already have an ID")).body(null);
        }
        Anexos result = anexosService.save(anexos);
        return ResponseEntity.created(new URI("/api/anexos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /anexos : Updates an existing anexos.
     *
     * @param anexos the anexos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated anexos,
     * or with status 400 (Bad Request) if the anexos is not valid,
     * or with status 500 (Internal Server Error) if the anexos couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/anexos")
    @Timed
    public ResponseEntity<Anexos> updateAnexos(@RequestBody Anexos anexos) throws URISyntaxException {
        log.debug("REST request to update Anexos : {}", anexos);
        if (anexos.getId() == null) {
            return createAnexos(anexos);
        }
        Anexos result = anexosService.save(anexos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, anexos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /anexos : get all the anexos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of anexos in body
     */
    @GetMapping("/anexos")
    @Timed
    public ResponseEntity<List<Anexos>> getAllAnexos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Anexos");
        Page<Anexos> page = anexosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/anexos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /anexos/:id : get the "id" anexos.
     *
     * @param id the id of the anexos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the anexos, or with status 404 (Not Found)
     */
    @GetMapping("/anexos/{id}")
    @Timed
    public ResponseEntity<Anexos> getAnexos(@PathVariable String id) {
        log.debug("REST request to get Anexos : {}", id);
        Anexos anexos = anexosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(anexos));
    }

    /**
     * DELETE  /anexos/:id : delete the "id" anexos.
     *
     * @param id the id of the anexos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/anexos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAnexos(@PathVariable String id) {
        log.debug("REST request to delete Anexos : {}", id);
        anexosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
