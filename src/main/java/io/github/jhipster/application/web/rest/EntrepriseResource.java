package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.EntrepriseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.EntrepriseDTO;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Entreprise.
 */
@RestController
@RequestMapping("/api")
public class EntrepriseResource {

    private final Logger log = LoggerFactory.getLogger(EntrepriseResource.class);

    private static final String ENTITY_NAME = "entreprise";

    private final EntrepriseService entrepriseService;

    public EntrepriseResource(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    /**
     * POST  /entreprises : Create a new entreprise.
     *
     * @param entrepriseDTO the entrepriseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entrepriseDTO, or with status 400 (Bad Request) if the entreprise has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entreprises")
    @Timed
    public ResponseEntity<EntrepriseDTO> createEntreprise(@Valid @RequestBody EntrepriseDTO entrepriseDTO) throws URISyntaxException {
        log.debug("REST request to save Entreprise : {}", entrepriseDTO);
        if (entrepriseDTO.getId() != null) {
            throw new BadRequestAlertException("A new entreprise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntrepriseDTO result = entrepriseService.save(entrepriseDTO);
        return ResponseEntity.created(new URI("/api/entreprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entreprises : Updates an existing entreprise.
     *
     * @param entrepriseDTO the entrepriseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entrepriseDTO,
     * or with status 400 (Bad Request) if the entrepriseDTO is not valid,
     * or with status 500 (Internal Server Error) if the entrepriseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entreprises")
    @Timed
    public ResponseEntity<EntrepriseDTO> updateEntreprise(@Valid @RequestBody EntrepriseDTO entrepriseDTO) throws URISyntaxException {
        log.debug("REST request to update Entreprise : {}", entrepriseDTO);
        if (entrepriseDTO.getId() == null) {
            return createEntreprise(entrepriseDTO);
        }
        EntrepriseDTO result = entrepriseService.save(entrepriseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entrepriseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entreprises : get all the entreprises.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of entreprises in body
     */
    @GetMapping("/entreprises")
    @Timed
    public ResponseEntity<List<EntrepriseDTO>> getAllEntreprises(@ApiParam Pageable pageable, @RequestParam(required = false) String filter) {
        if ("siege-is-null".equals(filter)) {
            log.debug("REST request to get all Entreprises where siege is null");
            return new ResponseEntity<>(entrepriseService.findAllWhereSiegeIsNull(),
                    HttpStatus.OK);
        }
        if ("contact-is-null".equals(filter)) {
            log.debug("REST request to get all Entreprises where contact is null");
            return new ResponseEntity<>(entrepriseService.findAllWhereContactIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Entreprises");
        Page<EntrepriseDTO> page = entrepriseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entreprises");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /entreprises/:id : get the "id" entreprise.
     *
     * @param id the id of the entrepriseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entrepriseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entreprises/{id}")
    @Timed
    public ResponseEntity<EntrepriseDTO> getEntreprise(@PathVariable Long id) {
        log.debug("REST request to get Entreprise : {}", id);
        EntrepriseDTO entrepriseDTO = entrepriseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entrepriseDTO));
    }

    /**
     * DELETE  /entreprises/:id : delete the "id" entreprise.
     *
     * @param id the id of the entrepriseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entreprises/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Long id) {
        log.debug("REST request to delete Entreprise : {}", id);
        entrepriseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
