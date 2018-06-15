package io.github.jhipster.application.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("users", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.ConventionStage.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tuteur.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tuteur.class.getName() + ".conventionStages", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Professionnel.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Professionnel.class.getName() + ".conventionStages", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Professionnel.class.getName() + ".diplomes", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Etudiant.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Etudiant.class.getName() + ".conventionStages", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Etudiant.class.getName() + ".promotions", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Entreprise.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Entreprise.class.getName() + ".partenariats", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Entreprise.class.getName() + ".sites", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Entreprise.class.getName() + ".personnels", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Entreprise.class.getName() + ".taxes", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Groupe.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Groupe.class.getName() + ".entreprises", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Site.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Site.class.getName() + ".conventionStages", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Diplome.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Diplome.class.getName() + ".filieres", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Diplome.class.getName() + ".partenariats", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Diplome.class.getName() + ".intervenants", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Partenariat.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Partenariat.class.getName() + ".diplomes", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Filiere.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Filiere.class.getName() + ".promotions", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Promotion.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Promotion.class.getName() + ".etudiants", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Taxe.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Author.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
