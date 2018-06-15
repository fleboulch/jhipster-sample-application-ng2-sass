package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.EntrepriseService;
import io.github.jhipster.application.domain.Entreprise;
import io.github.jhipster.application.repository.EntrepriseRepository;
import io.github.jhipster.application.service.dto.EntrepriseDTO;
import io.github.jhipster.application.service.mapper.EntrepriseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Entreprise.
 */
@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService{

    private final Logger log = LoggerFactory.getLogger(EntrepriseServiceImpl.class);

    private final EntrepriseRepository entrepriseRepository;

    private final EntrepriseMapper entrepriseMapper;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, EntrepriseMapper entrepriseMapper) {
        this.entrepriseRepository = entrepriseRepository;
        this.entrepriseMapper = entrepriseMapper;
    }

    /**
     * Save a entreprise.
     *
     * @param entrepriseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntrepriseDTO save(EntrepriseDTO entrepriseDTO) {
        log.debug("Request to save Entreprise : {}", entrepriseDTO);
        Entreprise entreprise = entrepriseMapper.toEntity(entrepriseDTO);
        entreprise = entrepriseRepository.save(entreprise);
        return entrepriseMapper.toDto(entreprise);
    }

    /**
     *  Get all the entreprises.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EntrepriseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Entreprises");
        return entrepriseRepository.findAll(pageable)
            .map(entrepriseMapper::toDto);
    }


    /**
     *  get all the entreprises where Siege is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntrepriseDTO> findAllWhereSiegeIsNull() {
        log.debug("Request to get all entreprises where Siege is null");
        return StreamSupport
            .stream(entrepriseRepository.findAll().spliterator(), false)
            .filter(entreprise -> entreprise.getSiege() == null)
            .map(entrepriseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the entreprises where Contact is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntrepriseDTO> findAllWhereContactIsNull() {
        log.debug("Request to get all entreprises where Contact is null");
        return StreamSupport
            .stream(entrepriseRepository.findAll().spliterator(), false)
            .filter(entreprise -> entreprise.getContact() == null)
            .map(entrepriseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one entreprise by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EntrepriseDTO findOne(Long id) {
        log.debug("Request to get Entreprise : {}", id);
        Entreprise entreprise = entrepriseRepository.findOne(id);
        return entrepriseMapper.toDto(entreprise);
    }

    /**
     *  Delete the  entreprise by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Entreprise : {}", id);
        entrepriseRepository.delete(id);
    }
}
