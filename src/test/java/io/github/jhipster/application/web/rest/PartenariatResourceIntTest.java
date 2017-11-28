package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationNg2SassApp;

import io.github.jhipster.application.domain.Partenariat;
import io.github.jhipster.application.repository.PartenariatRepository;
import io.github.jhipster.application.service.PartenariatService;
import io.github.jhipster.application.service.dto.PartenariatDTO;
import io.github.jhipster.application.service.mapper.PartenariatMapper;
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
 * Test class for the PartenariatResource REST controller.
 *
 * @see PartenariatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationNg2SassApp.class)
public class PartenariatResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_DEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private PartenariatRepository partenariatRepository;

    @Autowired
    private PartenariatMapper partenariatMapper;

    @Autowired
    private PartenariatService partenariatService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPartenariatMockMvc;

    private Partenariat partenariat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PartenariatResource partenariatResource = new PartenariatResource(partenariatService);
        this.restPartenariatMockMvc = MockMvcBuilders.standaloneSetup(partenariatResource)
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
    public static Partenariat createEntity(EntityManager em) {
        Partenariat partenariat = new Partenariat()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN);
        return partenariat;
    }

    @Before
    public void initTest() {
        partenariat = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartenariat() throws Exception {
        int databaseSizeBeforeCreate = partenariatRepository.findAll().size();

        // Create the Partenariat
        PartenariatDTO partenariatDTO = partenariatMapper.toDto(partenariat);
        restPartenariatMockMvc.perform(post("/api/partenariats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partenariatDTO)))
            .andExpect(status().isCreated());

        // Validate the Partenariat in the database
        List<Partenariat> partenariatList = partenariatRepository.findAll();
        assertThat(partenariatList).hasSize(databaseSizeBeforeCreate + 1);
        Partenariat testPartenariat = partenariatList.get(partenariatList.size() - 1);
        assertThat(testPartenariat.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testPartenariat.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
    }

    @Test
    @Transactional
    public void createPartenariatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partenariatRepository.findAll().size();

        // Create the Partenariat with an existing ID
        partenariat.setId(1L);
        PartenariatDTO partenariatDTO = partenariatMapper.toDto(partenariat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartenariatMockMvc.perform(post("/api/partenariats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partenariatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Partenariat in the database
        List<Partenariat> partenariatList = partenariatRepository.findAll();
        assertThat(partenariatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPartenariats() throws Exception {
        // Initialize the database
        partenariatRepository.saveAndFlush(partenariat);

        // Get all the partenariatList
        restPartenariatMockMvc.perform(get("/api/partenariats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partenariat.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(sameInstant(DEFAULT_DATE_DEBUT))))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(sameInstant(DEFAULT_DATE_FIN))));
    }

    @Test
    @Transactional
    public void getPartenariat() throws Exception {
        // Initialize the database
        partenariatRepository.saveAndFlush(partenariat);

        // Get the partenariat
        restPartenariatMockMvc.perform(get("/api/partenariats/{id}", partenariat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(partenariat.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(sameInstant(DEFAULT_DATE_DEBUT)))
            .andExpect(jsonPath("$.dateFin").value(sameInstant(DEFAULT_DATE_FIN)));
    }

    @Test
    @Transactional
    public void getNonExistingPartenariat() throws Exception {
        // Get the partenariat
        restPartenariatMockMvc.perform(get("/api/partenariats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartenariat() throws Exception {
        // Initialize the database
        partenariatRepository.saveAndFlush(partenariat);
        int databaseSizeBeforeUpdate = partenariatRepository.findAll().size();

        // Update the partenariat
        Partenariat updatedPartenariat = partenariatRepository.findOne(partenariat.getId());
        updatedPartenariat
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN);
        PartenariatDTO partenariatDTO = partenariatMapper.toDto(updatedPartenariat);

        restPartenariatMockMvc.perform(put("/api/partenariats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partenariatDTO)))
            .andExpect(status().isOk());

        // Validate the Partenariat in the database
        List<Partenariat> partenariatList = partenariatRepository.findAll();
        assertThat(partenariatList).hasSize(databaseSizeBeforeUpdate);
        Partenariat testPartenariat = partenariatList.get(partenariatList.size() - 1);
        assertThat(testPartenariat.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testPartenariat.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingPartenariat() throws Exception {
        int databaseSizeBeforeUpdate = partenariatRepository.findAll().size();

        // Create the Partenariat
        PartenariatDTO partenariatDTO = partenariatMapper.toDto(partenariat);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPartenariatMockMvc.perform(put("/api/partenariats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(partenariatDTO)))
            .andExpect(status().isCreated());

        // Validate the Partenariat in the database
        List<Partenariat> partenariatList = partenariatRepository.findAll();
        assertThat(partenariatList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePartenariat() throws Exception {
        // Initialize the database
        partenariatRepository.saveAndFlush(partenariat);
        int databaseSizeBeforeDelete = partenariatRepository.findAll().size();

        // Get the partenariat
        restPartenariatMockMvc.perform(delete("/api/partenariats/{id}", partenariat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Partenariat> partenariatList = partenariatRepository.findAll();
        assertThat(partenariatList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Partenariat.class);
        Partenariat partenariat1 = new Partenariat();
        partenariat1.setId(1L);
        Partenariat partenariat2 = new Partenariat();
        partenariat2.setId(partenariat1.getId());
        assertThat(partenariat1).isEqualTo(partenariat2);
        partenariat2.setId(2L);
        assertThat(partenariat1).isNotEqualTo(partenariat2);
        partenariat1.setId(null);
        assertThat(partenariat1).isNotEqualTo(partenariat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PartenariatDTO.class);
        PartenariatDTO partenariatDTO1 = new PartenariatDTO();
        partenariatDTO1.setId(1L);
        PartenariatDTO partenariatDTO2 = new PartenariatDTO();
        assertThat(partenariatDTO1).isNotEqualTo(partenariatDTO2);
        partenariatDTO2.setId(partenariatDTO1.getId());
        assertThat(partenariatDTO1).isEqualTo(partenariatDTO2);
        partenariatDTO2.setId(2L);
        assertThat(partenariatDTO1).isNotEqualTo(partenariatDTO2);
        partenariatDTO1.setId(null);
        assertThat(partenariatDTO1).isNotEqualTo(partenariatDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(partenariatMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(partenariatMapper.fromId(null)).isNull();
    }
}
