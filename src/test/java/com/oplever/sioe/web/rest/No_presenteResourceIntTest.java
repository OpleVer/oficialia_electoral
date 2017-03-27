package com.oplever.sioe.web.rest;

import com.oplever.sioe.OficialiaElectoralApp;

import com.oplever.sioe.domain.No_presente;
import com.oplever.sioe.repository.No_presenteRepository;
import com.oplever.sioe.service.No_presenteService;
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
 * Test class for the No_presenteResource REST controller.
 *
 * @see No_presenteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OficialiaElectoralApp.class)
public class No_presenteResourceIntTest {

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
    private No_presenteRepository no_presenteRepository;

    @Autowired
    private No_presenteService no_presenteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restNo_presenteMockMvc;

    private No_presente no_presente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        No_presenteResource no_presenteResource = new No_presenteResource(no_presenteService);
        this.restNo_presenteMockMvc = MockMvcBuilders.standaloneSetup(no_presenteResource)
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
    public static No_presente createEntity() {
        No_presente no_presente = new No_presente()
            .id_numero_solicitud(DEFAULT_ID_NUMERO_SOLICITUD)
            .acuerdo(DEFAULT_ACUERDO)
            .acuerdoContentType(DEFAULT_ACUERDO_CONTENT_TYPE)
            .num_acuerdo(DEFAULT_NUM_ACUERDO)
            .notificacion(DEFAULT_NOTIFICACION)
            .notificacionContentType(DEFAULT_NOTIFICACION_CONTENT_TYPE)
            .num_notificacion(DEFAULT_NUM_NOTIFICACION);
        return no_presente;
    }

    @Before
    public void initTest() {
        no_presenteRepository.deleteAll();
        no_presente = createEntity();
    }

    @Test
    public void createNo_presente() throws Exception {
        int databaseSizeBeforeCreate = no_presenteRepository.findAll().size();

        // Create the No_presente
        restNo_presenteMockMvc.perform(post("/api/no-presentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(no_presente)))
            .andExpect(status().isCreated());

        // Validate the No_presente in the database
        List<No_presente> no_presenteList = no_presenteRepository.findAll();
        assertThat(no_presenteList).hasSize(databaseSizeBeforeCreate + 1);
        No_presente testNo_presente = no_presenteList.get(no_presenteList.size() - 1);
        assertThat(testNo_presente.getId_numero_solicitud()).isEqualTo(DEFAULT_ID_NUMERO_SOLICITUD);
        assertThat(testNo_presente.getAcuerdo()).isEqualTo(DEFAULT_ACUERDO);
        assertThat(testNo_presente.getAcuerdoContentType()).isEqualTo(DEFAULT_ACUERDO_CONTENT_TYPE);
        assertThat(testNo_presente.getNum_acuerdo()).isEqualTo(DEFAULT_NUM_ACUERDO);
        assertThat(testNo_presente.getNotificacion()).isEqualTo(DEFAULT_NOTIFICACION);
        assertThat(testNo_presente.getNotificacionContentType()).isEqualTo(DEFAULT_NOTIFICACION_CONTENT_TYPE);
        assertThat(testNo_presente.getNum_notificacion()).isEqualTo(DEFAULT_NUM_NOTIFICACION);
    }

    @Test
    public void createNo_presenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = no_presenteRepository.findAll().size();

        // Create the No_presente with an existing ID
        no_presente.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restNo_presenteMockMvc.perform(post("/api/no-presentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(no_presente)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<No_presente> no_presenteList = no_presenteRepository.findAll();
        assertThat(no_presenteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllNo_presentes() throws Exception {
        // Initialize the database
        no_presenteRepository.save(no_presente);

        // Get all the no_presenteList
        restNo_presenteMockMvc.perform(get("/api/no-presentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(no_presente.getId())))
            .andExpect(jsonPath("$.[*].id_numero_solicitud").value(hasItem(DEFAULT_ID_NUMERO_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].acuerdoContentType").value(hasItem(DEFAULT_ACUERDO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acuerdo").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACUERDO))))
            .andExpect(jsonPath("$.[*].num_acuerdo").value(hasItem(DEFAULT_NUM_ACUERDO.toString())))
            .andExpect(jsonPath("$.[*].notificacionContentType").value(hasItem(DEFAULT_NOTIFICACION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION))))
            .andExpect(jsonPath("$.[*].num_notificacion").value(hasItem(DEFAULT_NUM_NOTIFICACION.toString())));
    }

    @Test
    public void getNo_presente() throws Exception {
        // Initialize the database
        no_presenteRepository.save(no_presente);

        // Get the no_presente
        restNo_presenteMockMvc.perform(get("/api/no-presentes/{id}", no_presente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(no_presente.getId()))
            .andExpect(jsonPath("$.id_numero_solicitud").value(DEFAULT_ID_NUMERO_SOLICITUD.toString()))
            .andExpect(jsonPath("$.acuerdoContentType").value(DEFAULT_ACUERDO_CONTENT_TYPE))
            .andExpect(jsonPath("$.acuerdo").value(Base64Utils.encodeToString(DEFAULT_ACUERDO)))
            .andExpect(jsonPath("$.num_acuerdo").value(DEFAULT_NUM_ACUERDO.toString()))
            .andExpect(jsonPath("$.notificacionContentType").value(DEFAULT_NOTIFICACION_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION)))
            .andExpect(jsonPath("$.num_notificacion").value(DEFAULT_NUM_NOTIFICACION.toString()));
    }

    @Test
    public void getNonExistingNo_presente() throws Exception {
        // Get the no_presente
        restNo_presenteMockMvc.perform(get("/api/no-presentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNo_presente() throws Exception {
        // Initialize the database
        no_presenteService.save(no_presente);

        int databaseSizeBeforeUpdate = no_presenteRepository.findAll().size();

        // Update the no_presente
        No_presente updatedNo_presente = no_presenteRepository.findOne(no_presente.getId());
        updatedNo_presente
            .id_numero_solicitud(UPDATED_ID_NUMERO_SOLICITUD)
            .acuerdo(UPDATED_ACUERDO)
            .acuerdoContentType(UPDATED_ACUERDO_CONTENT_TYPE)
            .num_acuerdo(UPDATED_NUM_ACUERDO)
            .notificacion(UPDATED_NOTIFICACION)
            .notificacionContentType(UPDATED_NOTIFICACION_CONTENT_TYPE)
            .num_notificacion(UPDATED_NUM_NOTIFICACION);

        restNo_presenteMockMvc.perform(put("/api/no-presentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNo_presente)))
            .andExpect(status().isOk());

        // Validate the No_presente in the database
        List<No_presente> no_presenteList = no_presenteRepository.findAll();
        assertThat(no_presenteList).hasSize(databaseSizeBeforeUpdate);
        No_presente testNo_presente = no_presenteList.get(no_presenteList.size() - 1);
        assertThat(testNo_presente.getId_numero_solicitud()).isEqualTo(UPDATED_ID_NUMERO_SOLICITUD);
        assertThat(testNo_presente.getAcuerdo()).isEqualTo(UPDATED_ACUERDO);
        assertThat(testNo_presente.getAcuerdoContentType()).isEqualTo(UPDATED_ACUERDO_CONTENT_TYPE);
        assertThat(testNo_presente.getNum_acuerdo()).isEqualTo(UPDATED_NUM_ACUERDO);
        assertThat(testNo_presente.getNotificacion()).isEqualTo(UPDATED_NOTIFICACION);
        assertThat(testNo_presente.getNotificacionContentType()).isEqualTo(UPDATED_NOTIFICACION_CONTENT_TYPE);
        assertThat(testNo_presente.getNum_notificacion()).isEqualTo(UPDATED_NUM_NOTIFICACION);
    }

    @Test
    public void updateNonExistingNo_presente() throws Exception {
        int databaseSizeBeforeUpdate = no_presenteRepository.findAll().size();

        // Create the No_presente

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNo_presenteMockMvc.perform(put("/api/no-presentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(no_presente)))
            .andExpect(status().isCreated());

        // Validate the No_presente in the database
        List<No_presente> no_presenteList = no_presenteRepository.findAll();
        assertThat(no_presenteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteNo_presente() throws Exception {
        // Initialize the database
        no_presenteService.save(no_presente);

        int databaseSizeBeforeDelete = no_presenteRepository.findAll().size();

        // Get the no_presente
        restNo_presenteMockMvc.perform(delete("/api/no-presentes/{id}", no_presente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<No_presente> no_presenteList = no_presenteRepository.findAll();
        assertThat(no_presenteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(No_presente.class);
    }
}
