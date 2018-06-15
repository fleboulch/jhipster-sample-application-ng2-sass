package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Partenariat;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Partenariat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartenariatRepository extends JpaRepository<Partenariat, Long> {
    @Query("select distinct partenariat from Partenariat partenariat left join fetch partenariat.diplomes")
    List<Partenariat> findAllWithEagerRelationships();

    @Query("select partenariat from Partenariat partenariat left join fetch partenariat.diplomes where partenariat.id =:id")
    Partenariat findOneWithEagerRelationships(@Param("id") Long id);

}
