package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PromotionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Promotion and its DTO PromotionDTO.
 */
@Mapper(componentModel = "spring", uses = {FiliereMapper.class})
public interface PromotionMapper extends EntityMapper<PromotionDTO, Promotion> {

    @Mapping(source = "filiere.id", target = "filiereId")
    @Mapping(source = "filiere.niveau", target = "filiereNiveau")
    PromotionDTO toDto(Promotion promotion); 

    @Mapping(source = "filiereId", target = "filiere")
    @Mapping(target = "etudiants", ignore = true)
    Promotion toEntity(PromotionDTO promotionDTO);

    default Promotion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Promotion promotion = new Promotion();
        promotion.setId(id);
        return promotion;
    }
}
