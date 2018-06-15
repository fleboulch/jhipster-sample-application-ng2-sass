package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EtudiantService;
import io.github.jhipster.application.domain.Etudiant;
import io.github.jhipster.application.repository.EtudiantRepository;
import io.github.jhipster.application.service.dto.EtudiantDTO;
import io.github.jhipster.application.service.mapper.EtudiantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Etudiant.
 */
@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService{

    private final Logger log = LoggerFactory.getLogger(EtudiantServiceImpl.class);

    private final EtudiantRepository etudiantRepository;

    private final EtudiantMapper etudiantMapper;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
    }

    /**
     * Save a etudiant.
     *
     * @param etudiantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtudiantDTO save(EtudiantDTO etudiantDTO) {
        log.debug("Request to save Etudiant : {}", etudiantDTO);
        Etudiant etudiant = etudiantMapper.toEntity(etudiantDTO);
        etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.toDto(etudiant);
    }

    /**
     *  Get all the etudiants.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtudiantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etudiants");
        return etudiantRepository.findAll(pageable)
            .map(etudiantMapper::toDto);
    }

    /**
     *  Get one etudiant by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EtudiantDTO findOne(Long id) {
        log.debug("Request to get Etudiant : {}", id);
        Etudiant etudiant = etudiantRepository.findOneWithEagerRelationships(id);
        return etudiantMapper.toDto(etudiant);
    }

    /**
     *  Delete the  etudiant by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etudiant : {}", id);
        etudiantRepository.delete(id);
    }
}
