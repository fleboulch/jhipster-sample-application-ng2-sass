package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ConventionStageService;
import io.github.jhipster.application.domain.ConventionStage;
import io.github.jhipster.application.repository.ConventionStageRepository;
import io.github.jhipster.application.service.dto.ConventionStageDTO;
import io.github.jhipster.application.service.mapper.ConventionStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ConventionStage.
 */
@Service
@Transactional
public class ConventionStageServiceImpl implements ConventionStageService{

    private final Logger log = LoggerFactory.getLogger(ConventionStageServiceImpl.class);

    private final ConventionStageRepository conventionStageRepository;

    private final ConventionStageMapper conventionStageMapper;

    public ConventionStageServiceImpl(ConventionStageRepository conventionStageRepository, ConventionStageMapper conventionStageMapper) {
        this.conventionStageRepository = conventionStageRepository;
        this.conventionStageMapper = conventionStageMapper;
    }

    /**
     * Save a conventionStage.
     *
     * @param conventionStageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConventionStageDTO save(ConventionStageDTO conventionStageDTO) {
        log.debug("Request to save ConventionStage : {}", conventionStageDTO);
        ConventionStage conventionStage = conventionStageMapper.toEntity(conventionStageDTO);
        conventionStage = conventionStageRepository.save(conventionStage);
        return conventionStageMapper.toDto(conventionStage);
    }

    /**
     *  Get all the conventionStages.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConventionStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConventionStages");
        return conventionStageRepository.findAll(pageable)
            .map(conventionStageMapper::toDto);
    }

    /**
     *  Get one conventionStage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConventionStageDTO findOne(Long id) {
        log.debug("Request to get ConventionStage : {}", id);
        ConventionStage conventionStage = conventionStageRepository.findOne(id);
        return conventionStageMapper.toDto(conventionStage);
    }

    /**
     *  Delete the  conventionStage by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConventionStage : {}", id);
        conventionStageRepository.delete(id);
    }
}
