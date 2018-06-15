package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PartenariatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Partenariat and its DTO PartenariatDTO.
 */
@Mapper(componentModel = "spring", uses = {DiplomeMapper.class, EntrepriseMapper.class})
public interface PartenariatMapper extends EntityMapper<PartenariatDTO, Partenariat> {

    @Mapping(source = "entreprise.id", target = "entrepriseId")
    @Mapping(source = "entreprise.nom", target = "entrepriseNom")
    PartenariatDTO toDto(Partenariat partenariat); 

    @Mapping(source = "entrepriseId", target = "entreprise")
    Partenariat toEntity(PartenariatDTO partenariatDTO);

    default Partenariat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Partenariat partenariat = new Partenariat();
        partenariat.setId(id);
        return partenariat;
    }
}
