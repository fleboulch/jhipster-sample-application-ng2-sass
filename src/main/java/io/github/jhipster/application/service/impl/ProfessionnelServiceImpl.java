package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ProfessionnelService;
import io.github.jhipster.application.domain.Professionnel;
import io.github.jhipster.application.repository.ProfessionnelRepository;
import io.github.jhipster.application.service.dto.ProfessionnelDTO;
import io.github.jhipster.application.service.mapper.ProfessionnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Professionnel.
 */
@Service
@Transactional
public class ProfessionnelServiceImpl implements ProfessionnelService{

    private final Logger log = LoggerFactory.getLogger(ProfessionnelServiceImpl.class);

    private final ProfessionnelRepository professionnelRepository;

    private final ProfessionnelMapper professionnelMapper;

    public ProfessionnelServiceImpl(ProfessionnelRepository professionnelRepository, ProfessionnelMapper professionnelMapper) {
        this.professionnelRepository = professionnelRepository;
        this.professionnelMapper = professionnelMapper;
    }

    /**
     * Save a professionnel.
     *
     * @param professionnelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfessionnelDTO save(ProfessionnelDTO professionnelDTO) {
        log.debug("Request to save Professionnel : {}", professionnelDTO);
        Professionnel professionnel = professionnelMapper.toEntity(professionnelDTO);
        professionnel = professionnelRepository.save(professionnel);
        return professionnelMapper.toDto(professionnel);
    }

    /**
     *  Get all the professionnels.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfessionnelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Professionnels");
        return professionnelRepository.findAll(pageable)
            .map(professionnelMapper::toDto);
    }

    /**
     *  Get one professionnel by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProfessionnelDTO findOne(Long id) {
        log.debug("Request to get Professionnel : {}", id);
        Professionnel professionnel = professionnelRepository.findOneWithEagerRelationships(id);
        return professionnelMapper.toDto(professionnel);
    }

    /**
     *  Delete the  professionnel by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Professionnel : {}", id);
        professionnelRepository.delete(id);
    }
}
