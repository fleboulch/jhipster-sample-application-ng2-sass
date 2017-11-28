package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TuteurService;
import io.github.jhipster.application.domain.Tuteur;
import io.github.jhipster.application.repository.TuteurRepository;
import io.github.jhipster.application.service.dto.TuteurDTO;
import io.github.jhipster.application.service.mapper.TuteurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Tuteur.
 */
@Service
@Transactional
public class TuteurServiceImpl implements TuteurService{

    private final Logger log = LoggerFactory.getLogger(TuteurServiceImpl.class);

    private final TuteurRepository tuteurRepository;

    private final TuteurMapper tuteurMapper;

    public TuteurServiceImpl(TuteurRepository tuteurRepository, TuteurMapper tuteurMapper) {
        this.tuteurRepository = tuteurRepository;
        this.tuteurMapper = tuteurMapper;
    }

    /**
     * Save a tuteur.
     *
     * @param tuteurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TuteurDTO save(TuteurDTO tuteurDTO) {
        log.debug("Request to save Tuteur : {}", tuteurDTO);
        Tuteur tuteur = tuteurMapper.toEntity(tuteurDTO);
        tuteur = tuteurRepository.save(tuteur);
        return tuteurMapper.toDto(tuteur);
    }

    /**
     *  Get all the tuteurs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TuteurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tuteurs");
        return tuteurRepository.findAll(pageable)
            .map(tuteurMapper::toDto);
    }

    /**
     *  Get one tuteur by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TuteurDTO findOne(Long id) {
        log.debug("Request to get Tuteur : {}", id);
        Tuteur tuteur = tuteurRepository.findOne(id);
        return tuteurMapper.toDto(tuteur);
    }

    /**
     *  Delete the  tuteur by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tuteur : {}", id);
        tuteurRepository.delete(id);
    }
}
