package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationNg2SassApp;

import io.github.jhipster.application.domain.Filiere;
import io.github.jhipster.application.repository.FiliereRepository;
import io.github.jhipster.application.service.FiliereService;
import io.github.jhipster.application.service.dto.FiliereDTO;
import io.github.jhipster.application.service.mapper.FiliereMapper;
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
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FiliereResource REST controller.
 *
 * @see FiliereResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationNg2SassApp.class)
public class FiliereResourceIntTest {

    private static final String DEFAULT_NIVEAU = "AAAAAAAAAA";
    private static final String UPDATED_NIVEAU = "BBBBBBBBBB";

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private FiliereMapper filiereMapper;

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFiliereMockMvc;

    private Filiere filiere;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FiliereResource filiereResource = new FiliereResource(filiereService);
        this.restFiliereMockMvc = MockMvcBuilders.standaloneSetup(filiereResource)
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
    public static Filiere createEntity(EntityManager em) {
        Filiere filiere = new Filiere()
            .niveau(DEFAULT_NIVEAU);
        return filiere;
    }

    @Before
    public void initTest() {
        filiere = createEntity(em);
    }

    @Test
    @Transactional
    public void createFiliere() throws Exception {
        int databaseSizeBeforeCreate = filiereRepository.findAll().size();

        // Create the Filiere
        FiliereDTO filiereDTO = filiereMapper.toDto(filiere);
        restFiliereMockMvc.perform(post("/api/filieres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filiereDTO)))
            .andExpect(status().isCreated());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeCreate + 1);
        Filiere testFiliere = filiereList.get(filiereList.size() - 1);
        assertThat(testFiliere.getNiveau()).isEqualTo(DEFAULT_NIVEAU);
    }

    @Test
    @Transactional
    public void createFiliereWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filiereRepository.findAll().size();

        // Create the Filiere with an existing ID
        filiere.setId(1L);
        FiliereDTO filiereDTO = filiereMapper.toDto(filiere);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFiliereMockMvc.perform(post("/api/filieres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filiereDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNiveauIsRequired() throws Exception {
        int databaseSizeBeforeTest = filiereRepository.findAll().size();
        // set the field null
        filiere.setNiveau(null);

        // Create the Filiere, which fails.
        FiliereDTO filiereDTO = filiereMapper.toDto(filiere);

        restFiliereMockMvc.perform(post("/api/filieres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filiereDTO)))
            .andExpect(status().isBadRequest());

        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFilieres() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        // Get all the filiereList
        restFiliereMockMvc.perform(get("/api/filieres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(filiere.getId().intValue())))
            .andExpect(jsonPath("$.[*].niveau").value(hasItem(DEFAULT_NIVEAU.toString())));
    }

    @Test
    @Transactional
    public void getFiliere() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        // Get the filiere
        restFiliereMockMvc.perform(get("/api/filieres/{id}", filiere.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(filiere.getId().intValue()))
            .andExpect(jsonPath("$.niveau").value(DEFAULT_NIVEAU.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFiliere() throws Exception {
        // Get the filiere
        restFiliereMockMvc.perform(get("/api/filieres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFiliere() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();

        // Update the filiere
        Filiere updatedFiliere = filiereRepository.findOne(filiere.getId());
        updatedFiliere
            .niveau(UPDATED_NIVEAU);
        FiliereDTO filiereDTO = filiereMapper.toDto(updatedFiliere);

        restFiliereMockMvc.perform(put("/api/filieres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filiereDTO)))
            .andExpect(status().isOk());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
        Filiere testFiliere = filiereList.get(filiereList.size() - 1);
        assertThat(testFiliere.getNiveau()).isEqualTo(UPDATED_NIVEAU);
    }

    @Test
    @Transactional
    public void updateNonExistingFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();

        // Create the Filiere
        FiliereDTO filiereDTO = filiereMapper.toDto(filiere);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFiliereMockMvc.perform(put("/api/filieres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filiereDTO)))
            .andExpect(status().isCreated());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFiliere() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);
        int databaseSizeBeforeDelete = filiereRepository.findAll().size();

        // Get the filiere
        restFiliereMockMvc.perform(delete("/api/filieres/{id}", filiere.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Filiere.class);
        Filiere filiere1 = new Filiere();
        filiere1.setId(1L);
        Filiere filiere2 = new Filiere();
        filiere2.setId(filiere1.getId());
        assertThat(filiere1).isEqualTo(filiere2);
        filiere2.setId(2L);
        assertThat(filiere1).isNotEqualTo(filiere2);
        filiere1.setId(null);
        assertThat(filiere1).isNotEqualTo(filiere2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FiliereDTO.class);
        FiliereDTO filiereDTO1 = new FiliereDTO();
        filiereDTO1.setId(1L);
        FiliereDTO filiereDTO2 = new FiliereDTO();
        assertThat(filiereDTO1).isNotEqualTo(filiereDTO2);
        filiereDTO2.setId(filiereDTO1.getId());
        assertThat(filiereDTO1).isEqualTo(filiereDTO2);
        filiereDTO2.setId(2L);
        assertThat(filiereDTO1).isNotEqualTo(filiereDTO2);
        filiereDTO1.setId(null);
        assertThat(filiereDTO1).isNotEqualTo(filiereDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(filiereMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(filiereMapper.fromId(null)).isNull();
    }
}
