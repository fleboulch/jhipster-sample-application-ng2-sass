package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.DiplomeService;
import io.github.jhipster.application.domain.Diplome;
import io.github.jhipster.application.repository.DiplomeRepository;
import io.github.jhipster.application.service.dto.DiplomeDTO;
import io.github.jhipster.application.service.mapper.DiplomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Diplome.
 */
@Service
@Transactional
public class DiplomeServiceImpl implements DiplomeService{

    private final Logger log = LoggerFactory.getLogger(DiplomeServiceImpl.class);

    private final DiplomeRepository diplomeRepository;

    private final DiplomeMapper diplomeMapper;

    public DiplomeServiceImpl(DiplomeRepository diplomeRepository, DiplomeMapper diplomeMapper) {
        this.diplomeRepository = diplomeRepository;
        this.diplomeMapper = diplomeMapper;
    }

    /**
     * Save a diplome.
     *
     * @param diplomeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DiplomeDTO save(DiplomeDTO diplomeDTO) {
        log.debug("Request to save Diplome : {}", diplomeDTO);
        Diplome diplome = diplomeMapper.toEntity(diplomeDTO);
        diplome = diplomeRepository.save(diplome);
        return diplomeMapper.toDto(diplome);
    }

    /**
     *  Get all the diplomes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DiplomeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Diplomes");
        return diplomeRepository.findAll(pageable)
            .map(diplomeMapper::toDto);
    }

    /**
     *  Get one diplome by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DiplomeDTO findOne(Long id) {
        log.debug("Request to get Diplome : {}", id);
        Diplome diplome = diplomeRepository.findOne(id);
        return diplomeMapper.toDto(diplome);
    }

    /**
     *  Delete the  diplome by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Diplome : {}", id);
        diplomeRepository.delete(id);
    }
}
