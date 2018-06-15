package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.DiplomeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.DiplomeDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Diplome.
 */
@RestController
@RequestMapping("/api")
public class DiplomeResource {

    private final Logger log = LoggerFactory.getLogger(DiplomeResource.class);

    private static final String ENTITY_NAME = "diplome";

    private final DiplomeService diplomeService;

    public DiplomeResource(DiplomeService diplomeService) {
        this.diplomeService = diplomeService;
    }

    /**
     * POST  /diplomes : Create a new diplome.
     *
     * @param diplomeDTO the diplomeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diplomeDTO, or with status 400 (Bad Request) if the diplome has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/diplomes")
    @Timed
    public ResponseEntity<DiplomeDTO> createDiplome(@Valid @RequestBody DiplomeDTO diplomeDTO) throws URISyntaxException {
        log.debug("REST request to save Diplome : {}", diplomeDTO);
        if (diplomeDTO.getId() != null) {
            throw new BadRequestAlertException("A new diplome cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiplomeDTO result = diplomeService.save(diplomeDTO);
        return ResponseEntity.created(new URI("/api/diplomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diplomes : Updates an existing diplome.
     *
     * @param diplomeDTO the diplomeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diplomeDTO,
     * or with status 400 (Bad Request) if the diplomeDTO is not valid,
     * or with status 500 (Internal Server Error) if the diplomeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/diplomes")
    @Timed
    public ResponseEntity<DiplomeDTO> updateDiplome(@Valid @RequestBody DiplomeDTO diplomeDTO) throws URISyntaxException {
        log.debug("REST request to update Diplome : {}", diplomeDTO);
        if (diplomeDTO.getId() == null) {
            return createDiplome(diplomeDTO);
        }
        DiplomeDTO result = diplomeService.save(diplomeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diplomeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diplomes : get all the diplomes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diplomes in body
     */
    @GetMapping("/diplomes")
    @Timed
    public ResponseEntity<List<DiplomeDTO>> getAllDiplomes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Diplomes");
        Page<DiplomeDTO> page = diplomeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/diplomes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /diplomes/:id : get the "id" diplome.
     *
     * @param id the id of the diplomeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diplomeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/diplomes/{id}")
    @Timed
    public ResponseEntity<DiplomeDTO> getDiplome(@PathVariable Long id) {
        log.debug("REST request to get Diplome : {}", id);
        DiplomeDTO diplomeDTO = diplomeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(diplomeDTO));
    }

    /**
     * DELETE  /diplomes/:id : delete the "id" diplome.
     *
     * @param id the id of the diplomeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/diplomes/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiplome(@PathVariable Long id) {
        log.debug("REST request to delete Diplome : {}", id);
        diplomeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
