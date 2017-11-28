package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Diplome;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Diplome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {

}
