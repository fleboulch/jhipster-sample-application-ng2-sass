package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Taxe;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Taxe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxeRepository extends JpaRepository<Taxe, Long> {

}
