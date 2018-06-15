package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationNg2SassApp;

import io.github.jhipster.application.domain.ConventionStage;
import io.github.jhipster.application.repository.ConventionStageRepository;
import io.github.jhipster.application.service.ConventionStageService;
import io.github.jhipster.application.service.dto.ConventionStageDTO;
import io.github.jhipster.application.service.mapper.ConventionStageMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.sameInstant;
import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConventionStageResource REST controller.
 *
 * @see ConventionStageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationNg2SassApp.class)
public class ConventionStageResourceIntTest {

    private static final String DEFAULT_SUJET = "AAAAAAAAAA";
    private static final String UPDATED_SUJET = "BBBBBBBBBB";

    private static final String DEFAULT_FONCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_FONCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_COMPETENCES = "AAAAAAAAAA";
    private static final String UPDATED_COMPETENCES = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_DEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ConventionStageRepository conventionStageRepository;

    @Autowired
    private ConventionStageMapper conventionStageMapper;

    @Autowired
    private ConventionStageService conventionStageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConventionStageMockMvc;

    private ConventionStage conventionStage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConventionStageResource conventionStageResource = new ConventionStageResource(conventionStageService);
        this.restConventionStageMockMvc = MockMvcBuilders.standaloneSetup(conventionStageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConventionStage createEntity(EntityManager em) {
        ConventionStage conventionStage = new ConventionStage()
            .sujet(DEFAULT_SUJET)
            .fonctions(DEFAULT_FONCTIONS)
            .competences(DEFAULT_COMPETENCES)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN);
        return conventionStage;
    }

    @Before
    public void initTest() {
        conventionStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createConventionStage() throws Exception {
        int databaseSizeBeforeCreate = conventionStageRepository.findAll().size();

        // Create the ConventionStage
        ConventionStageDTO conventionStageDTO = conventionStageMapper.toDto(conventionStage);
        restConventionStageMockMvc.perform(post("/api/convention-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conventionStageDTO)))
            .andExpect(status().isCreated());

        // Validate the ConventionStage in the database
        List<ConventionStage> conventionStageList = conventionStageRepository.findAll();
        assertThat(conventionStageList).hasSize(databaseSizeBeforeCreate + 1);
        ConventionStage testConventionStage = conventionStageList.get(conventionStageList.size() - 1);
        assertThat(testConventionStage.getSujet()).isEqualTo(DEFAULT_SUJET);
        assertThat(testConventionStage.getFonctions()).isEqualTo(DEFAULT_FONCTIONS);
        assertThat(testConventionStage.getCompetences()).isEqualTo(DEFAULT_COMPETENCES);
        assertThat(testConventionStage.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testConventionStage.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
    }

    @Test
    @Transactional
    public void createConventionStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conventionStageRepository.findAll().size();

        // Create the ConventionStage with an existing ID
        conventionStage.setId(1L);
        ConventionStageDTO conventionStageDTO = conventionStageMapper.toDto(conventionStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConventionStageMockMvc.perform(post("/api/convention-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conventionStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConventionStage in the database
        List<ConventionStage> conventionStageList = conventionStageRepository.findAll();
        assertThat(conventionStageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConventionStages() throws Exception {
        // Initialize the database
        conventionStageRepository.saveAndFlush(conventionStage);

        // Get all the conventionStageList
        restConventionStageMockMvc.perform(get("/api/convention-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conventionStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].sujet").value(hasItem(DEFAULT_SUJET.toString())))
            .andExpect(jsonPath("$.[*].fonctions").value(hasItem(DEFAULT_FONCTIONS.toString())))
            .andExpect(jsonPath("$.[*].competences").value(hasItem(DEFAULT_COMPETENCES.toString())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(sameInstant(DEFAULT_DATE_DEBUT))))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(sameInstant(DEFAULT_DATE_FIN))));
    }

    @Test
    @Transactional
    public void getConventionStage() throws Exception {
        // Initialize the database
        conventionStageRepository.saveAndFlush(conventionStage);

        // Get the conventionStage
        restConventionStageMockMvc.perform(get("/api/convention-stages/{id}", conventionStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conventionStage.getId().intValue()))
            .andExpect(jsonPath("$.sujet").value(DEFAULT_SUJET.toString()))
            .andExpect(jsonPath("$.fonctions").value(DEFAULT_FONCTIONS.toString()))
            .andExpect(jsonPath("$.competences").value(DEFAULT_COMPETENCES.toString()))
            .andExpect(jsonPath("$.dateDebut").value(sameInstant(DEFAULT_DATE_DEBUT)))
            .andExpect(jsonPath("$.dateFin").value(sameInstant(DEFAULT_DATE_FIN)));
    }

    @Test
    @Transactional
    public void getNonExistingConventionStage() throws Exception {
        // Get the conventionStage
        restConventionStageMockMvc.perform(get("/api/convention-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConventionStage() throws Exception {
        // Initialize the database
        conventionStageRepository.saveAndFlush(conventionStage);
        int databaseSizeBeforeUpdate = conventionStageRepository.findAll().size();

        // Update the conventionStage
        ConventionStage updatedConventionStage = conventionStageRepository.findOne(conventionStage.getId());
        updatedConventionStage
            .sujet(UPDATED_SUJET)
            .fonctions(UPDATED_FONCTIONS)
            .competences(UPDATED_COMPETENCES)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN);
        ConventionStageDTO conventionStageDTO = conventionStageMapper.toDto(updatedConventionStage);

        restConventionStageMockMvc.perform(put("/api/convention-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conventionStageDTO)))
            .andExpect(status().isOk());

        // Validate the ConventionStage in the database
        List<ConventionStage> conventionStageList = conventionStageRepository.findAll();
        assertThat(conventionStageList).hasSize(databaseSizeBeforeUpdate);
        ConventionStage testConventionStage = conventionStageList.get(conventionStageList.size() - 1);
        assertThat(testConventionStage.getSujet()).isEqualTo(UPDATED_SUJET);
        assertThat(testConventionStage.getFonctions()).isEqualTo(UPDATED_FONCTIONS);
        assertThat(testConventionStage.getCompetences()).isEqualTo(UPDATED_COMPETENCES);
        assertThat(testConventionStage.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testConventionStage.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingConventionStage() throws Exception {
        int databaseSizeBeforeUpdate = conventionStageRepository.findAll().size();

        // Create the ConventionStage
        ConventionStageDTO conventionStageDTO = conventionStageMapper.toDto(conventionStage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConventionStageMockMvc.perform(put("/api/convention-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conventionStageDTO)))
            .andExpect(status().isCreated());

        // Validate the ConventionStage in the database
        List<ConventionStage> conventionStageList = conventionStageRepository.findAll();
        assertThat(conventionStageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConventionStage() throws Exception {
        // Initialize the database
        conventionStageRepository.saveAndFlush(conventionStage);
        int databaseSizeBeforeDelete = conventionStageRepository.findAll().size();

        // Get the conventionStage
        restConventionStageMockMvc.perform(delete("/api/convention-stages/{id}", conventionStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConventionStage> conventionStageList = conventionStageRepository.findAll();
        assertThat(conventionStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConventionStage.class);
        ConventionStage conventionStage1 = new ConventionStage();
        conventionStage1.setId(1L);
        ConventionStage conventionStage2 = new ConventionStage();
        conventionStage2.setId(conventionStage1.getId());
        assertThat(conventionStage1).isEqualTo(conventionStage2);
        conventionStage2.setId(2L);
        assertThat(conventionStage1).isNotEqualTo(conventionStage2);
        conventionStage1.setId(null);
        assertThat(conventionStage1).isNotEqualTo(conventionStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConventionStageDTO.class);
        ConventionStageDTO conventionStageDTO1 = new ConventionStageDTO();
        conventionStageDTO1.setId(1L);
        ConventionStageDTO conventionStageDTO2 = new ConventionStageDTO();
        assertThat(conventionStageDTO1).isNotEqualTo(conventionStageDTO2);
        conventionStageDTO2.setId(conventionStageDTO1.getId());
        assertThat(conventionStageDTO1).isEqualTo(conventionStageDTO2);
        conventionStageDTO2.setId(2L);
        assertThat(conventionStageDTO1).isNotEqualTo(conventionStageDTO2);
        conventionStageDTO1.setId(null);
        assertThat(conventionStageDTO1).isNotEqualTo(conventionStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(conventionStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(conventionStageMapper.fromId(null)).isNull();
    }
}
