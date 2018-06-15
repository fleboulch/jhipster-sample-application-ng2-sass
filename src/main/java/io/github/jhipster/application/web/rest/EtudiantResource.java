package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.EtudiantService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.EtudiantDTO;
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
 * REST controller for managing Etudiant.
 */
@RestController
@RequestMapping("/api")
public class EtudiantResource {

    private final Logger log = LoggerFactory.getLogger(EtudiantResource.class);

    private static final String ENTITY_NAME = "etudiant";

    private final EtudiantService etudiantService;

    public EtudiantResource(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    /**
     * POST  /etudiants : Create a new etudiant.
     *
     * @param etudiantDTO the etudiantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etudiantDTO, or with status 400 (Bad Request) if the etudiant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etudiants")
    @Timed
    public ResponseEntity<EtudiantDTO> createEtudiant(@Valid @RequestBody EtudiantDTO etudiantDTO) throws URISyntaxException {
        log.debug("REST request to save Etudiant : {}", etudiantDTO);
        if (etudiantDTO.getId() != null) {
            throw new BadRequestAlertException("A new etudiant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtudiantDTO result = etudiantService.save(etudiantDTO);
        return ResponseEntity.created(new URI("/api/etudiants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etudiants : Updates an existing etudiant.
     *
     * @param etudiantDTO the etudiantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etudiantDTO,
     * or with status 400 (Bad Request) if the etudiantDTO is not valid,
     * or with status 500 (Internal Server Error) if the etudiantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etudiants")
    @Timed
    public ResponseEntity<EtudiantDTO> updateEtudiant(@Valid @RequestBody EtudiantDTO etudiantDTO) throws URISyntaxException {
        log.debug("REST request to update Etudiant : {}", etudiantDTO);
        if (etudiantDTO.getId() == null) {
            return createEtudiant(etudiantDTO);
        }
        EtudiantDTO result = etudiantService.save(etudiantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, etudiantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etudiants : get all the etudiants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of etudiants in body
     */
    @GetMapping("/etudiants")
    @Timed
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Etudiants");
        Page<EtudiantDTO> page = etudiantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/etudiants");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /etudiants/:id : get the "id" etudiant.
     *
     * @param id the id of the etudiantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etudiantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/etudiants/{id}")
    @Timed
    public ResponseEntity<EtudiantDTO> getEtudiant(@PathVariable Long id) {
        log.debug("REST request to get Etudiant : {}", id);
        EtudiantDTO etudiantDTO = etudiantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(etudiantDTO));
    }

    /**
     * DELETE  /etudiants/:id : delete the "id" etudiant.
     *
     * @param id the id of the etudiantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etudiants/{id}")
    @Timed
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        log.debug("REST request to delete Etudiant : {}", id);
        etudiantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
