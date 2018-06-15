package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.DiplomeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Diplome and its DTO DiplomeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiplomeMapper extends EntityMapper<DiplomeDTO, Diplome> {

    

    @Mapping(target = "filieres", ignore = true)
    @Mapping(target = "partenariats", ignore = true)
    @Mapping(target = "intervenants", ignore = true)
    Diplome toEntity(DiplomeDTO diplomeDTO);

    default Diplome fromId(Long id) {
        if (id == null) {
            return null;
        }
        Diplome diplome = new Diplome();
        diplome.setId(id);
        return diplome;
    }
}
