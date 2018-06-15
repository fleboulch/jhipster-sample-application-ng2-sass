package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.SiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Site and its DTO SiteDTO.
 */
@Mapper(componentModel = "spring", uses = {EntrepriseMapper.class})
public interface SiteMapper extends EntityMapper<SiteDTO, Site> {

    @Mapping(source = "entrepriseSiege.id", target = "entrepriseSiegeId")
    @Mapping(source = "entrepriseSite.id", target = "entrepriseSiteId")
    @Mapping(source = "entrepriseSite.nom", target = "entrepriseSiteNom")
    SiteDTO toDto(Site site); 

    @Mapping(source = "entrepriseSiegeId", target = "entrepriseSiege")
    @Mapping(target = "conventionStages", ignore = true)
    @Mapping(source = "entrepriseSiteId", target = "entrepriseSite")
    Site toEntity(SiteDTO siteDTO);

    default Site fromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
