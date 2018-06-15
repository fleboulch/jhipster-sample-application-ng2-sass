package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.DiplomeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Diplome.
 */
public interface DiplomeService {

    /**
     * Save a diplome.
     *
     * @param diplomeDTO the entity to save
     * @return the persisted entity
     */
    DiplomeDTO save(DiplomeDTO diplomeDTO);

    /**
     *  Get all the diplomes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DiplomeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" diplome.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DiplomeDTO findOne(Long id);

    /**
     *  Delete the "id" diplome.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
