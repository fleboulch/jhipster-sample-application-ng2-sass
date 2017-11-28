package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationNg2SassApp;

import io.github.jhipster.application.domain.Diplome;
import io.github.jhipster.application.repository.DiplomeRepository;
import io.github.jhipster.application.service.DiplomeService;
import io.github.jhipster.application.service.dto.DiplomeDTO;
import io.github.jhipster.application.service.mapper.DiplomeMapper;
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
 * Test class for the DiplomeResource REST controller.
 *
 * @see DiplomeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationNg2SassApp.class)
public class DiplomeResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Long DEFAULT_DATE_CREATION = 1L;
    private static final Long UPDATED_DATE_CREATION = 2L;

    private static final Long DEFAULT_DATE_MODIFICATION = 1L;
    private static final Long UPDATED_DATE_MODIFICATION = 2L;

    @Autowired
    private DiplomeRepository diplomeRepository;

    @Autowired
    private DiplomeMapper diplomeMapper;

    @Autowired
    private DiplomeService diplomeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiplomeMockMvc;

    private Diplome diplome;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiplomeResource diplomeResource = new DiplomeResource(diplomeService);
        this.restDiplomeMockMvc = MockMvcBuilders.standaloneSetup(diplomeResource)
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
    public static Diplome createEntity(EntityManager em) {
        Diplome diplome = new Diplome()
            .nom(DEFAULT_NOM)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateModification(DEFAULT_DATE_MODIFICATION);
        return diplome;
    }

    @Before
    public void initTest() {
        diplome = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiplome() throws Exception {
        int databaseSizeBeforeCreate = diplomeRepository.findAll().size();

        // Create the Diplome
        DiplomeDTO diplomeDTO = diplomeMapper.toDto(diplome);
        restDiplomeMockMvc.perform(post("/api/diplomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diplomeDTO)))
            .andExpect(status().isCreated());

        // Validate the Diplome in the database
        List<Diplome> diplomeList = diplomeRepository.findAll();
        assertThat(diplomeList).hasSize(databaseSizeBeforeCreate + 1);
        Diplome testDiplome = diplomeList.get(diplomeList.size() - 1);
        assertThat(testDiplome.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDiplome.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testDiplome.getDateModification()).isEqualTo(DEFAULT_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void createDiplomeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diplomeRepository.findAll().size();

        // Create the Diplome with an existing ID
        diplome.setId(1L);
        DiplomeDTO diplomeDTO = diplomeMapper.toDto(diplome);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiplomeMockMvc.perform(post("/api/diplomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diplomeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Diplome in the database
        List<Diplome> diplomeList = diplomeRepository.findAll();
        assertThat(diplomeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = diplomeRepository.findAll().size();
        // set the field null
        diplome.setNom(null);

        // Create the Diplome, which fails.
        DiplomeDTO diplomeDTO = diplomeMapper.toDto(diplome);

        restDiplomeMockMvc.perform(post("/api/diplomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diplomeDTO)))
            .andExpect(status().isBadRequest());

        List<Diplome> diplomeList = diplomeRepository.findAll();
        assertThat(diplomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDiplomes() throws Exception {
        // Initialize the database
        diplomeRepository.saveAndFlush(diplome);

        // Get all the diplomeList
        restDiplomeMockMvc.perform(get("/api/diplomes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diplome.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.intValue())))
            .andExpect(jsonPath("$.[*].dateModification").value(hasItem(DEFAULT_DATE_MODIFICATION.intValue())));
    }

    @Test
    @Transactional
    public void getDiplome() throws Exception {
        // Initialize the database
        diplomeRepository.saveAndFlush(diplome);

        // Get the diplome
        restDiplomeMockMvc.perform(get("/api/diplomes/{id}", diplome.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(diplome.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.intValue()))
            .andExpect(jsonPath("$.dateModification").value(DEFAULT_DATE_MODIFICATION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDiplome() throws Exception {
        // Get the diplome
        restDiplomeMockMvc.perform(get("/api/diplomes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiplome() throws Exception {
        // Initialize the database
        diplomeRepository.saveAndFlush(diplome);
        int databaseSizeBeforeUpdate = diplomeRepository.findAll().size();

        // Update the diplome
        Diplome updatedDiplome = diplomeRepository.findOne(diplome.getId());
        updatedDiplome
            .nom(UPDATED_NOM)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModification(UPDATED_DATE_MODIFICATION);
        DiplomeDTO diplomeDTO = diplomeMapper.toDto(updatedDiplome);

        restDiplomeMockMvc.perform(put("/api/diplomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diplomeDTO)))
            .andExpect(status().isOk());

        // Validate the Diplome in the database
        List<Diplome> diplomeList = diplomeRepository.findAll();
        assertThat(diplomeList).hasSize(databaseSizeBeforeUpdate);
        Diplome testDiplome = diplomeList.get(diplomeList.size() - 1);
        assertThat(testDiplome.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDiplome.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testDiplome.getDateModification()).isEqualTo(UPDATED_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDiplome() throws Exception {
        int databaseSizeBeforeUpdate = diplomeRepository.findAll().size();

        // Create the Diplome
        DiplomeDTO diplomeDTO = diplomeMapper.toDto(diplome);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDiplomeMockMvc.perform(put("/api/diplomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diplomeDTO)))
            .andExpect(status().isCreated());

        // Validate the Diplome in the database
        List<Diplome> diplomeList = diplomeRepository.findAll();
        assertThat(diplomeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDiplome() throws Exception {
        // Initialize the database
        diplomeRepository.saveAndFlush(diplome);
        int databaseSizeBeforeDelete = diplomeRepository.findAll().size();

        // Get the diplome
        restDiplomeMockMvc.perform(delete("/api/diplomes/{id}", diplome.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Diplome> diplomeList = diplomeRepository.findAll();
        assertThat(diplomeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Diplome.class);
        Diplome diplome1 = new Diplome();
        diplome1.setId(1L);
        Diplome diplome2 = new Diplome();
        diplome2.setId(diplome1.getId());
        assertThat(diplome1).isEqualTo(diplome2);
        diplome2.setId(2L);
        assertThat(diplome1).isNotEqualTo(diplome2);
        diplome1.setId(null);
        assertThat(diplome1).isNotEqualTo(diplome2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiplomeDTO.class);
        DiplomeDTO diplomeDTO1 = new DiplomeDTO();
        diplomeDTO1.setId(1L);
        DiplomeDTO diplomeDTO2 = new DiplomeDTO();
        assertThat(diplomeDTO1).isNotEqualTo(diplomeDTO2);
        diplomeDTO2.setId(diplomeDTO1.getId());
        assertThat(diplomeDTO1).isEqualTo(diplomeDTO2);
        diplomeDTO2.setId(2L);
        assertThat(diplomeDTO1).isNotEqualTo(diplomeDTO2);
        diplomeDTO1.setId(null);
        assertThat(diplomeDTO1).isNotEqualTo(diplomeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(diplomeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(diplomeMapper.fromId(null)).isNull();
    }
}
