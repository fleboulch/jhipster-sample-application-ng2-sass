package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ProfessionnelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Professionnel and its DTO ProfessionnelDTO.
 */
@Mapper(componentModel = "spring", uses = {EntrepriseMapper.class, DiplomeMapper.class})
public interface ProfessionnelMapper extends EntityMapper<ProfessionnelDTO, Professionnel> {

    @Mapping(source = "entrepriseContact.id", target = "entrepriseContactId")
    @Mapping(source = "entreprisePersonnel.id", target = "entreprisePersonnelId")
    @Mapping(source = "entreprisePersonnel.nom", target = "entreprisePersonnelNom")
    ProfessionnelDTO toDto(Professionnel professionnel); 

    @Mapping(source = "entrepriseContactId", target = "entrepriseContact")
    @Mapping(target = "conventionStages", ignore = true)
    @Mapping(source = "entreprisePersonnelId", target = "entreprisePersonnel")
    Professionnel toEntity(ProfessionnelDTO professionnelDTO);

    default Professionnel fromId(Long id) {
        if (id == null) {
            return null;
        }
        Professionnel professionnel = new Professionnel();
        professionnel.setId(id);
        return professionnel;
    }
}
