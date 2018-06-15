package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.ConventionStageService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ConventionStageDTO;
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
 * REST controller for managing ConventionStage.
 */
@RestController
@RequestMapping("/api")
public class ConventionStageResource {

    private final Logger log = LoggerFactory.getLogger(ConventionStageResource.class);

    private static final String ENTITY_NAME = "conventionStage";

    private final ConventionStageService conventionStageService;

    public ConventionStageResource(ConventionStageService conventionStageService) {
        this.conventionStageService = conventionStageService;
    }

    /**
     * POST  /convention-stages : Create a new conventionStage.
     *
     * @param conventionStageDTO the conventionStageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conventionStageDTO, or with status 400 (Bad Request) if the conventionStage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/convention-stages")
    @Timed
    public ResponseEntity<ConventionStageDTO> createConventionStage(@RequestBody ConventionStageDTO conventionStageDTO) throws URISyntaxException {
        log.debug("REST request to save ConventionStage : {}", conventionStageDTO);
        if (conventionStageDTO.getId() != null) {
            throw new BadRequestAlertException("A new conventionStage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConventionStageDTO result = conventionStageService.save(conventionStageDTO);
        return ResponseEntity.created(new URI("/api/convention-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /convention-stages : Updates an existing conventionStage.
     *
     * @param conventionStageDTO the conventionStageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conventionStageDTO,
     * or with status 400 (Bad Request) if the conventionStageDTO is not valid,
     * or with status 500 (Internal Server Error) if the conventionStageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/convention-stages")
    @Timed
    public ResponseEntity<ConventionStageDTO> updateConventionStage(@RequestBody ConventionStageDTO conventionStageDTO) throws URISyntaxException {
        log.debug("REST request to update ConventionStage : {}", conventionStageDTO);
        if (conventionStageDTO.getId() == null) {
            return createConventionStage(conventionStageDTO);
        }
        ConventionStageDTO result = conventionStageService.save(conventionStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conventionStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /convention-stages : get all the conventionStages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conventionStages in body
     */
    @GetMapping("/convention-stages")
    @Timed
    public ResponseEntity<List<ConventionStageDTO>> getAllConventionStages(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ConventionStages");
        Page<ConventionStageDTO> page = conventionStageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/convention-stages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /convention-stages/:id : get the "id" conventionStage.
     *
     * @param id the id of the conventionStageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conventionStageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/convention-stages/{id}")
    @Timed
    public ResponseEntity<ConventionStageDTO> getConventionStage(@PathVariable Long id) {
        log.debug("REST request to get ConventionStage : {}", id);
        ConventionStageDTO conventionStageDTO = conventionStageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(conventionStageDTO));
    }

    /**
     * DELETE  /convention-stages/:id : delete the "id" conventionStage.
     *
     * @param id the id of the conventionStageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/convention-stages/{id}")
    @Timed
    public ResponseEntity<Void> deleteConventionStage(@PathVariable Long id) {
        log.debug("REST request to delete ConventionStage : {}", id);
        conventionStageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
