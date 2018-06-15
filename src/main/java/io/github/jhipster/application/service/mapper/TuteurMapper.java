package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TuteurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tuteur and its DTO TuteurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TuteurMapper extends EntityMapper<TuteurDTO, Tuteur> {

    

    @Mapping(target = "conventionStages", ignore = true)
    Tuteur toEntity(TuteurDTO tuteurDTO);

    default Tuteur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tuteur tuteur = new Tuteur();
        tuteur.setId(id);
        return tuteur;
    }
}
