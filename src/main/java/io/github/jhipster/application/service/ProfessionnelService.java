package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ProfessionnelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Professionnel.
 */
public interface ProfessionnelService {

    /**
     * Save a professionnel.
     *
     * @param professionnelDTO the entity to save
     * @return the persisted entity
     */
    ProfessionnelDTO save(ProfessionnelDTO professionnelDTO);

    /**
     *  Get all the professionnels.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProfessionnelDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" professionnel.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProfessionnelDTO findOne(Long id);

    /**
     *  Delete the "id" professionnel.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
