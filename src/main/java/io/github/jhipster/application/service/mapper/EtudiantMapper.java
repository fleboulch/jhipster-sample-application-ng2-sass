package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EtudiantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Etudiant and its DTO EtudiantDTO.
 */
@Mapper(componentModel = "spring", uses = {PromotionMapper.class})
public interface EtudiantMapper extends EntityMapper<EtudiantDTO, Etudiant> {

    

    @Mapping(target = "conventionStages", ignore = true)
    Etudiant toEntity(EtudiantDTO etudiantDTO);

    default Etudiant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        return etudiant;
    }
}
