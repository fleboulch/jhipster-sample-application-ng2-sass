package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TaxeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Taxe and its DTO TaxeDTO.
 */
@Mapper(componentModel = "spring", uses = {EntrepriseMapper.class})
public interface TaxeMapper extends EntityMapper<TaxeDTO, Taxe> {

    @Mapping(source = "entreprise.id", target = "entrepriseId")
    @Mapping(source = "entreprise.nom", target = "entrepriseNom")
    TaxeDTO toDto(Taxe taxe); 

    @Mapping(source = "entrepriseId", target = "entreprise")
    Taxe toEntity(TaxeDTO taxeDTO);

    default Taxe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Taxe taxe = new Taxe();
        taxe.setId(id);
        return taxe;
    }
}
