package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ConventionStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConventionStage and its DTO ConventionStageDTO.
 */
@Mapper(componentModel = "spring", uses = {EtudiantMapper.class, SiteMapper.class, TuteurMapper.class, ProfessionnelMapper.class})
public interface ConventionStageMapper extends EntityMapper<ConventionStageDTO, ConventionStage> {

    @Mapping(source = "etudiant.id", target = "etudiantId")
    @Mapping(source = "etudiant.nom", target = "etudiantNom")
    @Mapping(source = "lieuStage.id", target = "lieuStageId")
    @Mapping(source = "lieuStage.adresse", target = "lieuStageAdresse")
    @Mapping(source = "tuteur.id", target = "tuteurId")
    @Mapping(source = "tuteur.nom", target = "tuteurNom")
    @Mapping(source = "maitreStage.id", target = "maitreStageId")
    @Mapping(source = "maitreStage.nom", target = "maitreStageNom")
    ConventionStageDTO toDto(ConventionStage conventionStage); 

    @Mapping(source = "etudiantId", target = "etudiant")
    @Mapping(source = "lieuStageId", target = "lieuStage")
    @Mapping(source = "tuteurId", target = "tuteur")
    @Mapping(source = "maitreStageId", target = "maitreStage")
    ConventionStage toEntity(ConventionStageDTO conventionStageDTO);

    default ConventionStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConventionStage conventionStage = new ConventionStage();
        conventionStage.setId(id);
        return conventionStage;
    }
}
