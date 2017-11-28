package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ConventionStage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConventionStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConventionStageRepository extends JpaRepository<ConventionStage, Long> {

}
