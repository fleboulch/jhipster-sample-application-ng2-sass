package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.PartenariatService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.PartenariatDTO;
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
 * REST controller for managing Partenariat.
 */
@RestController
@RequestMapping("/api")
public class PartenariatResource {

    private final Logger log = LoggerFactory.getLogger(PartenariatResource.class);

    private static final String ENTITY_NAME = "partenariat";

    private final PartenariatService partenariatService;

    public PartenariatResource(PartenariatService partenariatService) {
        this.partenariatService = partenariatService;
    }

    /**
     * POST  /partenariats : Create a new partenariat.
     *
     * @param partenariatDTO the partenariatDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new partenariatDTO, or with status 400 (Bad Request) if the partenariat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/partenariats")
    @Timed
    public ResponseEntity<PartenariatDTO> createPartenariat(@RequestBody PartenariatDTO partenariatDTO) throws URISyntaxException {
        log.debug("REST request to save Partenariat : {}", partenariatDTO);
        if (partenariatDTO.getId() != null) {
            throw new BadRequestAlertException("A new partenariat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartenariatDTO result = partenariatService.save(partenariatDTO);
        return ResponseEntity.created(new URI("/api/partenariats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /partenariats : Updates an existing partenariat.
     *
     * @param partenariatDTO the partenariatDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated partenariatDTO,
     * or with status 400 (Bad Request) if the partenariatDTO is not valid,
     * or with status 500 (Internal Server Error) if the partenariatDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/partenariats")
    @Timed
    public ResponseEntity<PartenariatDTO> updatePartenariat(@RequestBody PartenariatDTO partenariatDTO) throws URISyntaxException {
        log.debug("REST request to update Partenariat : {}", partenariatDTO);
        if (partenariatDTO.getId() == null) {
            return createPartenariat(partenariatDTO);
        }
        PartenariatDTO result = partenariatService.save(partenariatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, partenariatDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /partenariats : get all the partenariats.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of partenariats in body
     */
    @GetMapping("/partenariats")
    @Timed
    public ResponseEntity<List<PartenariatDTO>> getAllPartenariats(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Partenariats");
        Page<PartenariatDTO> page = partenariatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partenariats");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /partenariats/:id : get the "id" partenariat.
     *
     * @param id the id of the partenariatDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the partenariatDTO, or with status 404 (Not Found)
     */
    @GetMapping("/partenariats/{id}")
    @Timed
    public ResponseEntity<PartenariatDTO> getPartenariat(@PathVariable Long id) {
        log.debug("REST request to get Partenariat : {}", id);
        PartenariatDTO partenariatDTO = partenariatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(partenariatDTO));
    }

    /**
     * DELETE  /partenariats/:id : delete the "id" partenariat.
     *
     * @param id the id of the partenariatDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/partenariats/{id}")
    @Timed
    public ResponseEntity<Void> deletePartenariat(@PathVariable Long id) {
        log.debug("REST request to delete Partenariat : {}", id);
        partenariatService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
