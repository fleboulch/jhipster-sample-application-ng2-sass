package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.ProfessionnelService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ProfessionnelDTO;
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
 * REST controller for managing Professionnel.
 */
@RestController
@RequestMapping("/api")
public class ProfessionnelResource {

    private final Logger log = LoggerFactory.getLogger(ProfessionnelResource.class);

    private static final String ENTITY_NAME = "professionnel";

    private final ProfessionnelService professionnelService;

    public ProfessionnelResource(ProfessionnelService professionnelService) {
        this.professionnelService = professionnelService;
    }

    /**
     * POST  /professionnels : Create a new professionnel.
     *
     * @param professionnelDTO the professionnelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new professionnelDTO, or with status 400 (Bad Request) if the professionnel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/professionnels")
    @Timed
    public ResponseEntity<ProfessionnelDTO> createProfessionnel(@RequestBody ProfessionnelDTO professionnelDTO) throws URISyntaxException {
        log.debug("REST request to save Professionnel : {}", professionnelDTO);
        if (professionnelDTO.getId() != null) {
            throw new BadRequestAlertException("A new professionnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfessionnelDTO result = professionnelService.save(professionnelDTO);
        return ResponseEntity.created(new URI("/api/professionnels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /professionnels : Updates an existing professionnel.
     *
     * @param professionnelDTO the professionnelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated professionnelDTO,
     * or with status 400 (Bad Request) if the professionnelDTO is not valid,
     * or with status 500 (Internal Server Error) if the professionnelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/professionnels")
    @Timed
    public ResponseEntity<ProfessionnelDTO> updateProfessionnel(@RequestBody ProfessionnelDTO professionnelDTO) throws URISyntaxException {
        log.debug("REST request to update Professionnel : {}", professionnelDTO);
        if (professionnelDTO.getId() == null) {
            return createProfessionnel(professionnelDTO);
        }
        ProfessionnelDTO result = professionnelService.save(professionnelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, professionnelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /professionnels : get all the professionnels.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of professionnels in body
     */
    @GetMapping("/professionnels")
    @Timed
    public ResponseEntity<List<ProfessionnelDTO>> getAllProfessionnels(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Professionnels");
        Page<ProfessionnelDTO> page = professionnelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/professionnels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /professionnels/:id : get the "id" professionnel.
     *
     * @param id the id of the professionnelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the professionnelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/professionnels/{id}")
    @Timed
    public ResponseEntity<ProfessionnelDTO> getProfessionnel(@PathVariable Long id) {
        log.debug("REST request to get Professionnel : {}", id);
        ProfessionnelDTO professionnelDTO = professionnelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(professionnelDTO));
    }

    /**
     * DELETE  /professionnels/:id : delete the "id" professionnel.
     *
     * @param id the id of the professionnelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/professionnels/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfessionnel(@PathVariable Long id) {
        log.debug("REST request to delete Professionnel : {}", id);
        professionnelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
