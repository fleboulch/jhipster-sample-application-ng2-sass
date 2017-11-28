package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.GroupeService;
import io.github.jhipster.application.domain.Groupe;
import io.github.jhipster.application.repository.GroupeRepository;
import io.github.jhipster.application.service.dto.GroupeDTO;
import io.github.jhipster.application.service.mapper.GroupeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Groupe.
 */
@Service
@Transactional
public class GroupeServiceImpl implements GroupeService{

    private final Logger log = LoggerFactory.getLogger(GroupeServiceImpl.class);

    private final GroupeRepository groupeRepository;

    private final GroupeMapper groupeMapper;

    public GroupeServiceImpl(GroupeRepository groupeRepository, GroupeMapper groupeMapper) {
        this.groupeRepository = groupeRepository;
        this.groupeMapper = groupeMapper;
    }

    /**
     * Save a groupe.
     *
     * @param groupeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GroupeDTO save(GroupeDTO groupeDTO) {
        log.debug("Request to save Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    /**
     *  Get all the groupes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Groupes");
        return groupeRepository.findAll(pageable)
            .map(groupeMapper::toDto);
    }

    /**
     *  Get one groupe by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GroupeDTO findOne(Long id) {
        log.debug("Request to get Groupe : {}", id);
        Groupe groupe = groupeRepository.findOne(id);
        return groupeMapper.toDto(groupe);
    }

    /**
     *  Delete the  groupe by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Groupe : {}", id);
        groupeRepository.delete(id);
    }
}
