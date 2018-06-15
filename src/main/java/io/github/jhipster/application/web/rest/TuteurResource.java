package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.TuteurService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.TuteurDTO;
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
 * REST controller for managing Tuteur.
 */
@RestController
@RequestMapping("/api")
public class TuteurResource {

    private final Logger log = LoggerFactory.getLogger(TuteurResource.class);

    private static final String ENTITY_NAME = "tuteur";

    private final TuteurService tuteurService;

    public TuteurResource(TuteurService tuteurService) {
        this.tuteurService = tuteurService;
    }

    /**
     * POST  /tuteurs : Create a new tuteur.
     *
     * @param tuteurDTO the tuteurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tuteurDTO, or with status 400 (Bad Request) if the tuteur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tuteurs")
    @Timed
    public ResponseEntity<TuteurDTO> createTuteur(@RequestBody TuteurDTO tuteurDTO) throws URISyntaxException {
        log.debug("REST request to save Tuteur : {}", tuteurDTO);
        if (tuteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new tuteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TuteurDTO result = tuteurService.save(tuteurDTO);
        return ResponseEntity.created(new URI("/api/tuteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tuteurs : Updates an existing tuteur.
     *
     * @param tuteurDTO the tuteurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tuteurDTO,
     * or with status 400 (Bad Request) if the tuteurDTO is not valid,
     * or with status 500 (Internal Server Error) if the tuteurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tuteurs")
    @Timed
    public ResponseEntity<TuteurDTO> updateTuteur(@RequestBody TuteurDTO tuteurDTO) throws URISyntaxException {
        log.debug("REST request to update Tuteur : {}", tuteurDTO);
        if (tuteurDTO.getId() == null) {
            return createTuteur(tuteurDTO);
        }
        TuteurDTO result = tuteurService.save(tuteurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tuteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tuteurs : get all the tuteurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tuteurs in body
     */
    @GetMapping("/tuteurs")
    @Timed
    public ResponseEntity<List<TuteurDTO>> getAllTuteurs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Tuteurs");
        Page<TuteurDTO> page = tuteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tuteurs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tuteurs/:id : get the "id" tuteur.
     *
     * @param id the id of the tuteurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tuteurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tuteurs/{id}")
    @Timed
    public ResponseEntity<TuteurDTO> getTuteur(@PathVariable Long id) {
        log.debug("REST request to get Tuteur : {}", id);
        TuteurDTO tuteurDTO = tuteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tuteurDTO));
    }

    /**
     * DELETE  /tuteurs/:id : delete the "id" tuteur.
     *
     * @param id the id of the tuteurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tuteurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTuteur(@PathVariable Long id) {
        log.debug("REST request to delete Tuteur : {}", id);
        tuteurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
