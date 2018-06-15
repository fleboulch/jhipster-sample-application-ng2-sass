package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.FiliereDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Filiere and its DTO FiliereDTO.
 */
@Mapper(componentModel = "spring", uses = {DiplomeMapper.class})
public interface FiliereMapper extends EntityMapper<FiliereDTO, Filiere> {

    @Mapping(source = "diplome.id", target = "diplomeId")
    @Mapping(source = "diplome.nom", target = "diplomeNom")
    FiliereDTO toDto(Filiere filiere); 

    @Mapping(target = "promotions", ignore = true)
    @Mapping(source = "diplomeId", target = "diplome")
    Filiere toEntity(FiliereDTO filiereDTO);

    default Filiere fromId(Long id) {
        if (id == null) {
            return null;
        }
        Filiere filiere = new Filiere();
        filiere.setId(id);
        return filiere;
    }
}
