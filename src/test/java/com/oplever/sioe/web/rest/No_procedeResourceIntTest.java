package com.oplever.sioe.web.rest;

import com.oplever.sioe.OficialiaElectoralApp;

import com.oplever.sioe.domain.No_procede;
import com.oplever.sioe.repository.No_procedeRepository;
import com.oplever.sioe.service.No_procedeService;
import com.oplever.sioe.web.rest.errors.ExceptionTranslator;

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
import org.springframework.util.Base64Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the No_procedeResource REST controller.
 *
 * @see No_procedeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OficialiaElectoralApp.class)
public class No_procedeResourceIntTest {

    private static final String DEFAULT_ID_NUMERO_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMERO_SOLICITUD = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ACUERDO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACUERDO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACUERDO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACUERDO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NUM_ACUERDO = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ACUERDO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_NOTIFICACION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NUM_NOTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_NOTIFICACION = "BBBBBBBBBB";

    @Autowired
    private No_procedeRepository no_procedeRepository;

    @Autowired
    private No_procedeService no_procedeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restNo_procedeMockMvc;

    private No_procede no_procede;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        No_procedeResource no_procedeResource = new No_procedeResource(no_procedeService);
        this.restNo_procedeMockMvc = MockMvcBuilders.standaloneSetup(no_procedeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static No_procede createEntity() {
        No_procede no_procede = new No_procede()
            .id_numero_solicitud(DEFAULT_ID_NUMERO_SOLICITUD)
            .acuerdo(DEFAULT_ACUERDO)
            .acuerdoContentType(DEFAULT_ACUERDO_CONTENT_TYPE)
            .num_acuerdo(DEFAULT_NUM_ACUERDO)
            .notificacion(DEFAULT_NOTIFICACION)
            .notificacionContentType(DEFAULT_NOTIFICACION_CONTENT_TYPE)
            .num_notificacion(DEFAULT_NUM_NOTIFICACION);
        return no_procede;
    }

    @Before
    public void initTest() {
        no_procedeRepository.deleteAll();
        no_procede = createEntity();
    }

    @Test
    public void createNo_procede() throws Exception {
        int databaseSizeBeforeCreate = no_procedeRepository.findAll().size();

        // Create the No_procede
        restNo_procedeMockMvc.perform(post("/api/no-procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(no_procede)))
            .andExpect(status().isCreated());

        // Validate the No_procede in the database
        List<No_procede> no_procedeList = no_procedeRepository.findAll();
        assertThat(no_procedeList).hasSize(databaseSizeBeforeCreate + 1);
        No_procede testNo_procede = no_procedeList.get(no_procedeList.size() - 1);
        assertThat(testNo_procede.getId_numero_solicitud()).isEqualTo(DEFAULT_ID_NUMERO_SOLICITUD);
        assertThat(testNo_procede.getAcuerdo()).isEqualTo(DEFAULT_ACUERDO);
        assertThat(testNo_procede.getAcuerdoContentType()).isEqualTo(DEFAULT_ACUERDO_CONTENT_TYPE);
        assertThat(testNo_procede.getNum_acuerdo()).isEqualTo(DEFAULT_NUM_ACUERDO);
        assertThat(testNo_procede.getNotificacion()).isEqualTo(DEFAULT_NOTIFICACION);
        assertThat(testNo_procede.getNotificacionContentType()).isEqualTo(DEFAULT_NOTIFICACION_CONTENT_TYPE);
        assertThat(testNo_procede.getNum_notificacion()).isEqualTo(DEFAULT_NUM_NOTIFICACION);
    }

    @Test
    public void createNo_procedeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = no_procedeRepository.findAll().size();

        // Create the No_procede with an existing ID
        no_procede.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restNo_procedeMockMvc.perform(post("/api/no-procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(no_procede)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<No_procede> no_procedeList = no_procedeRepository.findAll();
        assertThat(no_procedeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllNo_procedes() throws Exception {
        // Initialize the database
        no_procedeRepository.save(no_procede);

        // Get all the no_procedeList
        restNo_procedeMockMvc.perform(get("/api/no-procedes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(no_procede.getId())))
            .andExpect(jsonPath("$.[*].id_numero_solicitud").value(hasItem(DEFAULT_ID_NUMERO_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].acuerdoContentType").value(hasItem(DEFAULT_ACUERDO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acuerdo").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACUERDO))))
            .andExpect(jsonPath("$.[*].num_acuerdo").value(hasItem(DEFAULT_NUM_ACUERDO.toString())))
            .andExpect(jsonPath("$.[*].notificacionContentType").value(hasItem(DEFAULT_NOTIFICACION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION))))
            .andExpect(jsonPath("$.[*].num_notificacion").value(hasItem(DEFAULT_NUM_NOTIFICACION.toString())));
    }

    @Test
    public void getNo_procede() throws Exception {
        // Initialize the database
        no_procedeRepository.save(no_procede);

        // Get the no_procede
        restNo_procedeMockMvc.perform(get("/api/no-procedes/{id}", no_procede.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(no_procede.getId()))
            .andExpect(jsonPath("$.id_numero_solicitud").value(DEFAULT_ID_NUMERO_SOLICITUD.toString()))
            .andExpect(jsonPath("$.acuerdoContentType").value(DEFAULT_ACUERDO_CONTENT_TYPE))
            .andExpect(jsonPath("$.acuerdo").value(Base64Utils.encodeToString(DEFAULT_ACUERDO)))
            .andExpect(jsonPath("$.num_acuerdo").value(DEFAULT_NUM_ACUERDO.toString()))
            .andExpect(jsonPath("$.notificacionContentType").value(DEFAULT_NOTIFICACION_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION)))
            .andExpect(jsonPath("$.num_notificacion").value(DEFAULT_NUM_NOTIFICACION.toString()));
    }

    @Test
    public void getNonExistingNo_procede() throws Exception {
        // Get the no_procede
        restNo_procedeMockMvc.perform(get("/api/no-procedes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNo_procede() throws Exception {
        // Initialize the database
        no_procedeService.save(no_procede);

        int databaseSizeBeforeUpdate = no_procedeRepository.findAll().size();

        // Update the no_procede
        No_procede updatedNo_procede = no_procedeRepository.findOne(no_procede.getId());
        updatedNo_procede
            .id_numero_solicitud(UPDATED_ID_NUMERO_SOLICITUD)
            .acuerdo(UPDATED_ACUERDO)
            .acuerdoContentType(UPDATED_ACUERDO_CONTENT_TYPE)
            .num_acuerdo(UPDATED_NUM_ACUERDO)
            .notificacion(UPDATED_NOTIFICACION)
            .notificacionContentType(UPDATED_NOTIFICACION_CONTENT_TYPE)
            .num_notificacion(UPDATED_NUM_NOTIFICACION);

        restNo_procedeMockMvc.perform(put("/api/no-procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNo_procede)))
            .andExpect(status().isOk());

        // Validate the No_procede in the database
        List<No_procede> no_procedeList = no_procedeRepository.findAll();
        assertThat(no_procedeList).hasSize(databaseSizeBeforeUpdate);
        No_procede testNo_procede = no_procedeList.get(no_procedeList.size() - 1);
        assertThat(testNo_procede.getId_numero_solicitud()).isEqualTo(UPDATED_ID_NUMERO_SOLICITUD);
        assertThat(testNo_procede.getAcuerdo()).isEqualTo(UPDATED_ACUERDO);
        assertThat(testNo_procede.getAcuerdoContentType()).isEqualTo(UPDATED_ACUERDO_CONTENT_TYPE);
        assertThat(testNo_procede.getNum_acuerdo()).isEqualTo(UPDATED_NUM_ACUERDO);
        assertThat(testNo_procede.getNotificacion()).isEqualTo(UPDATED_NOTIFICACION);
        assertThat(testNo_procede.getNotificacionContentType()).isEqualTo(UPDATED_NOTIFICACION_CONTENT_TYPE);
        assertThat(testNo_procede.getNum_notificacion()).isEqualTo(UPDATED_NUM_NOTIFICACION);
    }

    @Test
    public void updateNonExistingNo_procede() throws Exception {
        int databaseSizeBeforeUpdate = no_procedeRepository.findAll().size();

        // Create the No_procede

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNo_procedeMockMvc.perform(put("/api/no-procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(no_procede)))
            .andExpect(status().isCreated());

        // Validate the No_procede in the database
        List<No_procede> no_procedeList = no_procedeRepository.findAll();
        assertThat(no_procedeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteNo_procede() throws Exception {
        // Initialize the database
        no_procedeService.save(no_procede);

        int databaseSizeBeforeDelete = no_procedeRepository.findAll().size();

        // Get the no_procede
        restNo_procedeMockMvc.perform(delete("/api/no-procedes/{id}", no_procede.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<No_procede> no_procedeList = no_procedeRepository.findAll();
        assertThat(no_procedeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(No_procede.class);
    }
}
