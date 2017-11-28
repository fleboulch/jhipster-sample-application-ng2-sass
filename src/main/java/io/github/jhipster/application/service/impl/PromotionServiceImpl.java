package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PromotionService;
import io.github.jhipster.application.domain.Promotion;
import io.github.jhipster.application.repository.PromotionRepository;
import io.github.jhipster.application.service.dto.PromotionDTO;
import io.github.jhipster.application.service.mapper.PromotionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Promotion.
 */
@Service
@Transactional
public class PromotionServiceImpl implements PromotionService{

    private final Logger log = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;

    private final PromotionMapper promotionMapper;

    public PromotionServiceImpl(PromotionRepository promotionRepository, PromotionMapper promotionMapper) {
        this.promotionRepository = promotionRepository;
        this.promotionMapper = promotionMapper;
    }

    /**
     * Save a promotion.
     *
     * @param promotionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PromotionDTO save(PromotionDTO promotionDTO) {
        log.debug("Request to save Promotion : {}", promotionDTO);
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toDto(promotion);
    }

    /**
     *  Get all the promotions.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PromotionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Promotions");
        return promotionRepository.findAll(pageable)
            .map(promotionMapper::toDto);
    }

    /**
     *  Get one promotion by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PromotionDTO findOne(Long id) {
        log.debug("Request to get Promotion : {}", id);
        Promotion promotion = promotionRepository.findOne(id);
        return promotionMapper.toDto(promotion);
    }

    /**
     *  Delete the  promotion by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Promotion : {}", id);
        promotionRepository.delete(id);
    }
}
