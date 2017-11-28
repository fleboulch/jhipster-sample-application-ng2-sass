package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.EntrepriseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Entreprise and its DTO EntrepriseDTO.
 */
@Mapper(componentModel = "spring", uses = {GroupeMapper.class})
public interface EntrepriseMapper extends EntityMapper<EntrepriseDTO, Entreprise> {

    @Mapping(source = "groupe.id", target = "groupeId")
    @Mapping(source = "groupe.nom", target = "groupeNom")
    EntrepriseDTO toDto(Entreprise entreprise); 

    @Mapping(target = "partenariats", ignore = true)
    @Mapping(target = "sites", ignore = true)
    @Mapping(target = "personnels", ignore = true)
    @Mapping(target = "taxes", ignore = true)
    @Mapping(target = "siege", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(source = "groupeId", target = "groupe")
    Entreprise toEntity(EntrepriseDTO entrepriseDTO);

    default Entreprise fromId(Long id) {
        if (id == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return entreprise;
    }
}
