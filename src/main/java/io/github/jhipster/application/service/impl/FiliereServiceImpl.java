package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FiliereService;
import io.github.jhipster.application.domain.Filiere;
import io.github.jhipster.application.repository.FiliereRepository;
import io.github.jhipster.application.service.dto.FiliereDTO;
import io.github.jhipster.application.service.mapper.FiliereMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Filiere.
 */
@Service
@Transactional
public class FiliereServiceImpl implements FiliereService{

    private final Logger log = LoggerFactory.getLogger(FiliereServiceImpl.class);

    private final FiliereRepository filiereRepository;

    private final FiliereMapper filiereMapper;

    public FiliereServiceImpl(FiliereRepository filiereRepository, FiliereMapper filiereMapper) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
    }

    /**
     * Save a filiere.
     *
     * @param filiereDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FiliereDTO save(FiliereDTO filiereDTO) {
        log.debug("Request to save Filiere : {}", filiereDTO);
        Filiere filiere = filiereMapper.toEntity(filiereDTO);
        filiere = filiereRepository.save(filiere);
        return filiereMapper.toDto(filiere);
    }

    /**
     *  Get all the filieres.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FiliereDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Filieres");
        return filiereRepository.findAll(pageable)
            .map(filiereMapper::toDto);
    }

    /**
     *  Get one filiere by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FiliereDTO findOne(Long id) {
        log.debug("Request to get Filiere : {}", id);
        Filiere filiere = filiereRepository.findOne(id);
        return filiereMapper.toDto(filiere);
    }

    /**
     *  Delete the  filiere by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Filiere : {}", id);
        filiereRepository.delete(id);
    }
}
