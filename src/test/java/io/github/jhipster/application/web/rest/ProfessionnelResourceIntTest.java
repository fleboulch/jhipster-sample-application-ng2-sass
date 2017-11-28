package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationNg2SassApp;

import io.github.jhipster.application.domain.Professionnel;
import io.github.jhipster.application.repository.ProfessionnelRepository;
import io.github.jhipster.application.service.ProfessionnelService;
import io.github.jhipster.application.service.dto.ProfessionnelDTO;
import io.github.jhipster.application.service.mapper.ProfessionnelMapper;
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
 * Test class for the ProfessionnelResource REST controller.
 *
 * @see ProfessionnelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationNg2SassApp.class)
public class ProfessionnelResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FONCTION = "AAAAAAAAAA";
    private static final String UPDATED_FONCTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ANCIEN_ETUDIANT = false;
    private static final Boolean UPDATED_ANCIEN_ETUDIANT = true;

    private static final Long DEFAULT_DATE_CREATION = 1L;
    private static final Long UPDATED_DATE_CREATION = 2L;

    private static final Long DEFAULT_DATE_MODIFICATION = 1L;
    private static final Long UPDATED_DATE_MODIFICATION = 2L;

    @Autowired
    private ProfessionnelRepository professionnelRepository;

    @Autowired
    private ProfessionnelMapper professionnelMapper;

    @Autowired
    private ProfessionnelService professionnelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfessionnelMockMvc;

    private Professionnel professionnel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfessionnelResource professionnelResource = new ProfessionnelResource(professionnelService);
        this.restProfessionnelMockMvc = MockMvcBuilders.standaloneSetup(professionnelResource)
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
    public static Professionnel createEntity(EntityManager em) {
        Professionnel professionnel = new Professionnel()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .telephone(DEFAULT_TELEPHONE)
            .mail(DEFAULT_MAIL)
            .fonction(DEFAULT_FONCTION)
            .ancienEtudiant(DEFAULT_ANCIEN_ETUDIANT)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateModification(DEFAULT_DATE_MODIFICATION);
        return professionnel;
    }

    @Before
    public void initTest() {
        professionnel = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfessionnel() throws Exception {
        int databaseSizeBeforeCreate = professionnelRepository.findAll().size();

        // Create the Professionnel
        ProfessionnelDTO professionnelDTO = professionnelMapper.toDto(professionnel);
        restProfessionnelMockMvc.perform(post("/api/professionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professionnelDTO)))
            .andExpect(status().isCreated());

        // Validate the Professionnel in the database
        List<Professionnel> professionnelList = professionnelRepository.findAll();
        assertThat(professionnelList).hasSize(databaseSizeBeforeCreate + 1);
        Professionnel testProfessionnel = professionnelList.get(professionnelList.size() - 1);
        assertThat(testProfessionnel.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testProfessionnel.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testProfessionnel.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testProfessionnel.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testProfessionnel.getFonction()).isEqualTo(DEFAULT_FONCTION);
        assertThat(testProfessionnel.isAncienEtudiant()).isEqualTo(DEFAULT_ANCIEN_ETUDIANT);
        assertThat(testProfessionnel.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testProfessionnel.getDateModification()).isEqualTo(DEFAULT_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void createProfessionnelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = professionnelRepository.findAll().size();

        // Create the Professionnel with an existing ID
        professionnel.setId(1L);
        ProfessionnelDTO professionnelDTO = professionnelMapper.toDto(professionnel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfessionnelMockMvc.perform(post("/api/professionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professionnelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Professionnel in the database
        List<Professionnel> professionnelList = professionnelRepository.findAll();
        assertThat(professionnelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProfessionnels() throws Exception {
        // Initialize the database
        professionnelRepository.saveAndFlush(professionnel);

        // Get all the professionnelList
        restProfessionnelMockMvc.perform(get("/api/professionnels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(professionnel.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].fonction").value(hasItem(DEFAULT_FONCTION.toString())))
            .andExpect(jsonPath("$.[*].ancienEtudiant").value(hasItem(DEFAULT_ANCIEN_ETUDIANT.booleanValue())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.intValue())))
            .andExpect(jsonPath("$.[*].dateModification").value(hasItem(DEFAULT_DATE_MODIFICATION.intValue())));
    }

    @Test
    @Transactional
    public void getProfessionnel() throws Exception {
        // Initialize the database
        professionnelRepository.saveAndFlush(professionnel);

        // Get the professionnel
        restProfessionnelMockMvc.perform(get("/api/professionnels/{id}", professionnel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(professionnel.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.fonction").value(DEFAULT_FONCTION.toString()))
            .andExpect(jsonPath("$.ancienEtudiant").value(DEFAULT_ANCIEN_ETUDIANT.booleanValue()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.intValue()))
            .andExpect(jsonPath("$.dateModification").value(DEFAULT_DATE_MODIFICATION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProfessionnel() throws Exception {
        // Get the professionnel
        restProfessionnelMockMvc.perform(get("/api/professionnels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfessionnel() throws Exception {
        // Initialize the database
        professionnelRepository.saveAndFlush(professionnel);
        int databaseSizeBeforeUpdate = professionnelRepository.findAll().size();

        // Update the professionnel
        Professionnel updatedProfessionnel = professionnelRepository.findOne(professionnel.getId());
        updatedProfessionnel
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .telephone(UPDATED_TELEPHONE)
            .mail(UPDATED_MAIL)
            .fonction(UPDATED_FONCTION)
            .ancienEtudiant(UPDATED_ANCIEN_ETUDIANT)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModification(UPDATED_DATE_MODIFICATION);
        ProfessionnelDTO professionnelDTO = professionnelMapper.toDto(updatedProfessionnel);

        restProfessionnelMockMvc.perform(put("/api/professionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professionnelDTO)))
            .andExpect(status().isOk());

        // Validate the Professionnel in the database
        List<Professionnel> professionnelList = professionnelRepository.findAll();
        assertThat(professionnelList).hasSize(databaseSizeBeforeUpdate);
        Professionnel testProfessionnel = professionnelList.get(professionnelList.size() - 1);
        assertThat(testProfessionnel.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProfessionnel.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testProfessionnel.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testProfessionnel.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testProfessionnel.getFonction()).isEqualTo(UPDATED_FONCTION);
        assertThat(testProfessionnel.isAncienEtudiant()).isEqualTo(UPDATED_ANCIEN_ETUDIANT);
        assertThat(testProfessionnel.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testProfessionnel.getDateModification()).isEqualTo(UPDATED_DATE_MODIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingProfessionnel() throws Exception {
        int databaseSizeBeforeUpdate = professionnelRepository.findAll().size();

        // Create the Professionnel
        ProfessionnelDTO professionnelDTO = professionnelMapper.toDto(professionnel);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProfessionnelMockMvc.perform(put("/api/professionnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(professionnelDTO)))
            .andExpect(status().isCreated());

        // Validate the Professionnel in the database
        List<Professionnel> professionnelList = professionnelRepository.findAll();
        assertThat(professionnelList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProfessionnel() throws Exception {
        // Initialize the database
        professionnelRepository.saveAndFlush(professionnel);
        int databaseSizeBeforeDelete = professionnelRepository.findAll().size();

        // Get the professionnel
        restProfessionnelMockMvc.perform(delete("/api/professionnels/{id}", professionnel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Professionnel> professionnelList = professionnelRepository.findAll();
        assertThat(professionnelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Professionnel.class);
        Professionnel professionnel1 = new Professionnel();
        professionnel1.setId(1L);
        Professionnel professionnel2 = new Professionnel();
        professionnel2.setId(professionnel1.getId());
        assertThat(professionnel1).isEqualTo(professionnel2);
        professionnel2.setId(2L);
        assertThat(professionnel1).isNotEqualTo(professionnel2);
        professionnel1.setId(null);
        assertThat(professionnel1).isNotEqualTo(professionnel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfessionnelDTO.class);
        ProfessionnelDTO professionnelDTO1 = new ProfessionnelDTO();
        professionnelDTO1.setId(1L);
        ProfessionnelDTO professionnelDTO2 = new ProfessionnelDTO();
        assertThat(professionnelDTO1).isNotEqualTo(professionnelDTO2);
        professionnelDTO2.setId(professionnelDTO1.getId());
        assertThat(professionnelDTO1).isEqualTo(professionnelDTO2);
        professionnelDTO2.setId(2L);
        assertThat(professionnelDTO1).isNotEqualTo(professionnelDTO2);
        professionnelDTO1.setId(null);
        assertThat(professionnelDTO1).isNotEqualTo(professionnelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(professionnelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(professionnelMapper.fromId(null)).isNull();
    }
}
