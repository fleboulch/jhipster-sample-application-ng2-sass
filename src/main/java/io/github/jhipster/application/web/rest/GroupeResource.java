package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.GroupeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.GroupeDTO;
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
 * REST controller for managing Groupe.
 */
@RestController
@RequestMapping("/api")
public class GroupeResource {

    private final Logger log = LoggerFactory.getLogger(GroupeResource.class);

    private static final String ENTITY_NAME = "groupe";

    private final GroupeService groupeService;

    public GroupeResource(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    /**
     * POST  /groupes : Create a new groupe.
     *
     * @param groupeDTO the groupeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groupeDTO, or with status 400 (Bad Request) if the groupe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/groupes")
    @Timed
    public ResponseEntity<GroupeDTO> createGroupe(@RequestBody GroupeDTO groupeDTO) throws URISyntaxException {
        log.debug("REST request to save Groupe : {}", groupeDTO);
        if (groupeDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupeDTO result = groupeService.save(groupeDTO);
        return ResponseEntity.created(new URI("/api/groupes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /groupes : Updates an existing groupe.
     *
     * @param groupeDTO the groupeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groupeDTO,
     * or with status 400 (Bad Request) if the groupeDTO is not valid,
     * or with status 500 (Internal Server Error) if the groupeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/groupes")
    @Timed
    public ResponseEntity<GroupeDTO> updateGroupe(@RequestBody GroupeDTO groupeDTO) throws URISyntaxException {
        log.debug("REST request to update Groupe : {}", groupeDTO);
        if (groupeDTO.getId() == null) {
            return createGroupe(groupeDTO);
        }
        GroupeDTO result = groupeService.save(groupeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /groupes : get all the groupes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of groupes in body
     */
    @GetMapping("/groupes")
    @Timed
    public ResponseEntity<List<GroupeDTO>> getAllGroupes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Groupes");
        Page<GroupeDTO> page = groupeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/groupes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /groupes/:id : get the "id" groupe.
     *
     * @param id the id of the groupeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groupeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/groupes/{id}")
    @Timed
    public ResponseEntity<GroupeDTO> getGroupe(@PathVariable Long id) {
        log.debug("REST request to get Groupe : {}", id);
        GroupeDTO groupeDTO = groupeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(groupeDTO));
    }

    /**
     * DELETE  /groupes/:id : delete the "id" groupe.
     *
     * @param id the id of the groupeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/groupes/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroupe(@PathVariable Long id) {
        log.debug("REST request to delete Groupe : {}", id);
        groupeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
