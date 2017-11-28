package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Etudiant;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Etudiant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    @Query("select distinct etudiant from Etudiant etudiant left join fetch etudiant.promotions")
    List<Etudiant> findAllWithEagerRelationships();

    @Query("select etudiant from Etudiant etudiant left join fetch etudiant.promotions where etudiant.id =:id")
    Etudiant findOneWithEagerRelationships(@Param("id") Long id);

}
