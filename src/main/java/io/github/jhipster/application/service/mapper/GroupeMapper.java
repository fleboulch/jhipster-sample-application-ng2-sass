package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.GroupeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Groupe and its DTO GroupeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupeMapper extends EntityMapper<GroupeDTO, Groupe> {

    

    @Mapping(target = "entreprises", ignore = true)
    Groupe toEntity(GroupeDTO groupeDTO);

    default Groupe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Groupe groupe = new Groupe();
        groupe.setId(id);
        return groupe;
    }
}
