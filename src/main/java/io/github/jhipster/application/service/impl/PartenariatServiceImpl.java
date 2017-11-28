package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PartenariatService;
import io.github.jhipster.application.domain.Partenariat;
import io.github.jhipster.application.repository.PartenariatRepository;
import io.github.jhipster.application.service.dto.PartenariatDTO;
import io.github.jhipster.application.service.mapper.PartenariatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Partenariat.
 */
@Service
@Transactional
public class PartenariatServiceImpl implements PartenariatService{

    private final Logger log = LoggerFactory.getLogger(PartenariatServiceImpl.class);

    private final PartenariatRepository partenariatRepository;

    private final PartenariatMapper partenariatMapper;

    public PartenariatServiceImpl(PartenariatRepository partenariatRepository, PartenariatMapper partenariatMapper) {
        this.partenariatRepository = partenariatRepository;
        this.partenariatMapper = partenariatMapper;
    }

    /**
     * Save a partenariat.
     *
     * @param partenariatDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PartenariatDTO save(PartenariatDTO partenariatDTO) {
        log.debug("Request to save Partenariat : {}", partenariatDTO);
        Partenariat partenariat = partenariatMapper.toEntity(partenariatDTO);
        partenariat = partenariatRepository.save(partenariat);
        return partenariatMapper.toDto(partenariat);
    }

    /**
     *  Get all the partenariats.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PartenariatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Partenariats");
        return partenariatRepository.findAll(pageable)
            .map(partenariatMapper::toDto);
    }

    /**
     *  Get one partenariat by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PartenariatDTO findOne(Long id) {
        log.debug("Request to get Partenariat : {}", id);
        Partenariat partenariat = partenariatRepository.findOneWithEagerRelationships(id);
        return partenariatMapper.toDto(partenariat);
    }

    /**
     *  Delete the  partenariat by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Partenariat : {}", id);
        partenariatRepository.delete(id);
    }
}
