package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TuteurDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Tuteur.
 */
public interface TuteurService {

    /**
     * Save a tuteur.
     *
     * @param tuteurDTO the entity to save
     * @return the persisted entity
     */
    TuteurDTO save(TuteurDTO tuteurDTO);

    /**
     *  Get all the tuteurs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<TuteurDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" tuteur.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TuteurDTO findOne(Long id);

    /**
     *  Delete the "id" tuteur.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
