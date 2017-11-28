package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.EtudiantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Etudiant.
 */
public interface EtudiantService {

    /**
     * Save a etudiant.
     *
     * @param etudiantDTO the entity to save
     * @return the persisted entity
     */
    EtudiantDTO save(EtudiantDTO etudiantDTO);

    /**
     *  Get all the etudiants.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<EtudiantDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" etudiant.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EtudiantDTO findOne(Long id);

    /**
     *  Delete the "id" etudiant.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
