package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationNg2SassApp;

import io.github.jhipster.application.domain.Entreprise;
import io.github.jhipster.application.repository.EntrepriseRepository;
import io.github.jhipster.application.service.EntrepriseService;
import io.github.jhipster.application.service.dto.EntrepriseDTO;
import io.github.jhipster.application.service.mapper.EntrepriseMapper;
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
 * Test class for the EntrepriseResource REST controller.
 *
 * @see EntrepriseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationNg2SassApp.class)
public class EntrepriseResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_SIRET = "AAAAAAAAAA";
    private static final String UPDATED_NUM_SIRET = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_SIREN = "AAAAAAAAAA";
    private static final String UPDATED_NUM_SIREN = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final Long DEFAULT_DATE_CREATION = 1L;
    private static final Long UPDATED_DATE_CREATION = 2L;

    private static final Long DEFAULT_DATE_MODIFICATION = 1L;
    private static final Long UPDATED_DATE_MODIFICATION = 2L;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private EntrepriseMapper entrepriseMapper;

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntrepriseMockMvc;

    private Entreprise entreprise;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntrepriseResource entrepriseResource = new EntrepriseResource(entrepriseService);
        this.restEntrepriseMockMvc = MockMvcBuilders.standaloneSetup(entrepriseResource)
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
    public static Entreprise createEntity(EntityManager em) {
        Entreprise entreprise = new Entreprise()
            .nom(DEFAULT_NOM)
            .pays(DEFAULT_PAYS)
            .numSiret(DEFAULT_NUM_SIRET)
            .numSiren(DEFAULT_NUM_SIREN)
            .telephone(DEFAULT_TELEPHONE)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateModification(DEFAULT_DATE_MODIFICATION);
        return entreprise;
    }

    @Before
    public void initTest() {
        entreprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntreprise() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();

        // Create the Entreprise
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);
        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isCreated());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeCreate + 1);
        Entreprise testEntreprise = entrepriseList.get(entrepriseList.size() - 1);
        assertThat(testEntreprise.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEntreprise.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testEntreprise.getNumSiret()).isEqualTo(DEFAULT_NUM_SIRET);
        assertThat(testEntreprise.getNumSiren()).isEqualTo(DEFAULT_NUM_SIREN);
        assertThat(testEntreprise.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testEntreprise.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testEntreprise.getDateModification()).isEqualTo(DEFAULT_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void createEntrepriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();

        // Create the Entreprise with an existing ID
        entreprise.setId(1L);
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumSiretIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setNumSiret(null);

        // Create the Entreprise, which fails.
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);

        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumSirenIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setNumSiren(null);

        // Create the Entreprise, which fails.
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);

        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntreprises() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get all the entrepriseList
        restEntrepriseMockMvc.perform(get("/api/entreprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entreprise.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS.toString())))
            .andExpect(jsonPath("$.[*].numSiret").value(hasItem(DEFAULT_NUM_SIRET.toString())))
            .andExpect(jsonPath("$.[*].numSiren").value(hasItem(DEFAULT_NUM_SIREN.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.intValue())))
            .andExpect(jsonPath("$.[*].dateModification").value(hasItem(DEFAULT_DATE_MODIFICATION.intValue())));
    }

    @Test
    @Transactional
    public void getEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", entreprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entreprise.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS.toString()))
            .andExpect(jsonPath("$.numSiret").value(DEFAULT_NUM_SIRET.toString()))
            .andExpect(jsonPath("$.numSiren").value(DEFAULT_NUM_SIREN.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.intValue()))
            .andExpect(jsonPath("$.dateModification").value(DEFAULT_DATE_MODIFICATION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEntreprise() throws Exception {
        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);
        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // Update the entreprise
        Entreprise updatedEntreprise = entrepriseRepository.findOne(entreprise.getId());
        updatedEntreprise
            .nom(UPDATED_NOM)
            .pays(UPDATED_PAYS)
            .numSiret(UPDATED_NUM_SIRET)
            .numSiren(UPDATED_NUM_SIREN)
            .telephone(UPDATED_TELEPHONE)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModification(UPDATED_DATE_MODIFICATION);
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(updatedEntreprise);

        restEntrepriseMockMvc.perform(put("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isOk());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeUpdate);
        Entreprise testEntreprise = entrepriseList.get(entrepriseList.size() - 1);
        assertThat(testEntreprise.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEntreprise.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testEntreprise.getNumSiret()).isEqualTo(UPDATED_NUM_SIRET);
        assertThat(testEntreprise.getNumSiren()).isEqualTo(UPDATED_NUM_SIREN);
        assertThat(testEntreprise.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testEntreprise.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testEntreprise.getDateModification()).isEqualTo(UPDATED_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingEntreprise() throws Exception {
        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // Create the Entreprise
        EntrepriseDTO entrepriseDTO = entrepriseMapper.toDto(entreprise);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntrepriseMockMvc.perform(put("/api/entreprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
            .andExpect(status().isCreated());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);
        int databaseSizeBeforeDelete = entrepriseRepository.findAll().size();

        // Get the entreprise
        restEntrepriseMockMvc.perform(delete("/api/entreprises/{id}", entreprise.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entreprise.class);
        Entreprise entreprise1 = new Entreprise();
        entreprise1.setId(1L);
        Entreprise entreprise2 = new Entreprise();
        entreprise2.setId(entreprise1.getId());
        assertThat(entreprise1).isEqualTo(entreprise2);
        entreprise2.setId(2L);
        assertThat(entreprise1).isNotEqualTo(entreprise2);
        entreprise1.setId(null);
        assertThat(entreprise1).isNotEqualTo(entreprise2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntrepriseDTO.class);
        EntrepriseDTO entrepriseDTO1 = new EntrepriseDTO();
        entrepriseDTO1.setId(1L);
        EntrepriseDTO entrepriseDTO2 = new EntrepriseDTO();
        assertThat(entrepriseDTO1).isNotEqualTo(entrepriseDTO2);
        entrepriseDTO2.setId(entrepriseDTO1.getId());
        assertThat(entrepriseDTO1).isEqualTo(entrepriseDTO2);
        entrepriseDTO2.setId(2L);
        assertThat(entrepriseDTO1).isNotEqualTo(entrepriseDTO2);
        entrepriseDTO1.setId(null);
        assertThat(entrepriseDTO1).isNotEqualTo(entrepriseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entrepriseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entrepriseMapper.fromId(null)).isNull();
    }
}
