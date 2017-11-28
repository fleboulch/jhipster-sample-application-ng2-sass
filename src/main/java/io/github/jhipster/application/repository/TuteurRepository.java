package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Tuteur;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tuteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TuteurRepository extends JpaRepository<Tuteur, Long> {

}
