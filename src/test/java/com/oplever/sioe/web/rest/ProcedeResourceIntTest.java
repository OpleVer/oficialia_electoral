package com.oplever.sioe.web.rest;

import com.oplever.sioe.OficialiaElectoralApp;

import com.oplever.sioe.domain.Procede;
import com.oplever.sioe.repository.ProcedeRepository;
import com.oplever.sioe.service.ProcedeService;
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
 * Test class for the ProcedeResource REST controller.
 *
 * @see ProcedeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OficialiaElectoralApp.class)
public class ProcedeResourceIntTest {

    private static final byte[] DEFAULT_ACTA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACTA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACTA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACTA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ACUERDO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACUERDO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACUERDO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACUERDO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ID_PROCEDE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PROCEDE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_NOTIFICACION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ID_NUMERO_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMERO_SOLICITUD = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ACTA = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ACTA = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ACUERDO = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ACUERDO = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_NOTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_NOTIFICACION = "BBBBBBBBBB";

    @Autowired
    private ProcedeRepository procedeRepository;

    @Autowired
    private ProcedeService procedeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProcedeMockMvc;

    private Procede procede;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProcedeResource procedeResource = new ProcedeResource(procedeService);
        this.restProcedeMockMvc = MockMvcBuilders.standaloneSetup(procedeResource)
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
    public static Procede createEntity() {
        Procede procede = new Procede()
            .acta(DEFAULT_ACTA)
            .actaContentType(DEFAULT_ACTA_CONTENT_TYPE)
            .acuerdo(DEFAULT_ACUERDO)
            .acuerdoContentType(DEFAULT_ACUERDO_CONTENT_TYPE)
            .id_procede(DEFAULT_ID_PROCEDE)
            .notificacion(DEFAULT_NOTIFICACION)
            .notificacionContentType(DEFAULT_NOTIFICACION_CONTENT_TYPE)
            .id_numero_solicitud(DEFAULT_ID_NUMERO_SOLICITUD)
            .num_acta(DEFAULT_NUM_ACTA)
            .num_acuerdo(DEFAULT_NUM_ACUERDO)
            .num_notificacion(DEFAULT_NUM_NOTIFICACION);
        return procede;
    }

    @Before
    public void initTest() {
        procedeRepository.deleteAll();
        procede = createEntity();
    }

    @Test
    public void createProcede() throws Exception {
        int databaseSizeBeforeCreate = procedeRepository.findAll().size();

        // Create the Procede
        restProcedeMockMvc.perform(post("/api/procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procede)))
            .andExpect(status().isCreated());

        // Validate the Procede in the database
        List<Procede> procedeList = procedeRepository.findAll();
        assertThat(procedeList).hasSize(databaseSizeBeforeCreate + 1);
        Procede testProcede = procedeList.get(procedeList.size() - 1);
        assertThat(testProcede.getActa()).isEqualTo(DEFAULT_ACTA);
        assertThat(testProcede.getActaContentType()).isEqualTo(DEFAULT_ACTA_CONTENT_TYPE);
        assertThat(testProcede.getAcuerdo()).isEqualTo(DEFAULT_ACUERDO);
        assertThat(testProcede.getAcuerdoContentType()).isEqualTo(DEFAULT_ACUERDO_CONTENT_TYPE);
        assertThat(testProcede.getId_procede()).isEqualTo(DEFAULT_ID_PROCEDE);
        assertThat(testProcede.getNotificacion()).isEqualTo(DEFAULT_NOTIFICACION);
        assertThat(testProcede.getNotificacionContentType()).isEqualTo(DEFAULT_NOTIFICACION_CONTENT_TYPE);
        assertThat(testProcede.getId_numero_solicitud()).isEqualTo(DEFAULT_ID_NUMERO_SOLICITUD);
        assertThat(testProcede.getNum_acta()).isEqualTo(DEFAULT_NUM_ACTA);
        assertThat(testProcede.getNum_acuerdo()).isEqualTo(DEFAULT_NUM_ACUERDO);
        assertThat(testProcede.getNum_notificacion()).isEqualTo(DEFAULT_NUM_NOTIFICACION);
    }

    @Test
    public void createProcedeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = procedeRepository.findAll().size();

        // Create the Procede with an existing ID
        procede.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcedeMockMvc.perform(post("/api/procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procede)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Procede> procedeList = procedeRepository.findAll();
        assertThat(procedeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProcedes() throws Exception {
        // Initialize the database
        procedeRepository.save(procede);

        // Get all the procedeList
        restProcedeMockMvc.perform(get("/api/procedes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(procede.getId())))
            .andExpect(jsonPath("$.[*].actaContentType").value(hasItem(DEFAULT_ACTA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acta").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACTA))))
            .andExpect(jsonPath("$.[*].acuerdoContentType").value(hasItem(DEFAULT_ACUERDO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acuerdo").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACUERDO))))
            .andExpect(jsonPath("$.[*].id_procede").value(hasItem(DEFAULT_ID_PROCEDE.toString())))
            .andExpect(jsonPath("$.[*].notificacionContentType").value(hasItem(DEFAULT_NOTIFICACION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION))))
            .andExpect(jsonPath("$.[*].id_numero_solicitud").value(hasItem(DEFAULT_ID_NUMERO_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].num_acta").value(hasItem(DEFAULT_NUM_ACTA.toString())))
            .andExpect(jsonPath("$.[*].num_acuerdo").value(hasItem(DEFAULT_NUM_ACUERDO.toString())))
            .andExpect(jsonPath("$.[*].num_notificacion").value(hasItem(DEFAULT_NUM_NOTIFICACION.toString())));
    }

    @Test
    public void getProcede() throws Exception {
        // Initialize the database
        procedeRepository.save(procede);

        // Get the procede
        restProcedeMockMvc.perform(get("/api/procedes/{id}", procede.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(procede.getId()))
            .andExpect(jsonPath("$.actaContentType").value(DEFAULT_ACTA_CONTENT_TYPE))
            .andExpect(jsonPath("$.acta").value(Base64Utils.encodeToString(DEFAULT_ACTA)))
            .andExpect(jsonPath("$.acuerdoContentType").value(DEFAULT_ACUERDO_CONTENT_TYPE))
            .andExpect(jsonPath("$.acuerdo").value(Base64Utils.encodeToString(DEFAULT_ACUERDO)))
            .andExpect(jsonPath("$.id_procede").value(DEFAULT_ID_PROCEDE.toString()))
            .andExpect(jsonPath("$.notificacionContentType").value(DEFAULT_NOTIFICACION_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION)))
            .andExpect(jsonPath("$.id_numero_solicitud").value(DEFAULT_ID_NUMERO_SOLICITUD.toString()))
            .andExpect(jsonPath("$.num_acta").value(DEFAULT_NUM_ACTA.toString()))
            .andExpect(jsonPath("$.num_acuerdo").value(DEFAULT_NUM_ACUERDO.toString()))
            .andExpect(jsonPath("$.num_notificacion").value(DEFAULT_NUM_NOTIFICACION.toString()));
    }

    @Test
    public void getNonExistingProcede() throws Exception {
        // Get the procede
        restProcedeMockMvc.perform(get("/api/procedes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProcede() throws Exception {
        // Initialize the database
        procedeService.save(procede);

        int databaseSizeBeforeUpdate = procedeRepository.findAll().size();

        // Update the procede
        Procede updatedProcede = procedeRepository.findOne(procede.getId());
        updatedProcede
            .acta(UPDATED_ACTA)
            .actaContentType(UPDATED_ACTA_CONTENT_TYPE)
            .acuerdo(UPDATED_ACUERDO)
            .acuerdoContentType(UPDATED_ACUERDO_CONTENT_TYPE)
            .id_procede(UPDATED_ID_PROCEDE)
            .notificacion(UPDATED_NOTIFICACION)
            .notificacionContentType(UPDATED_NOTIFICACION_CONTENT_TYPE)
            .id_numero_solicitud(UPDATED_ID_NUMERO_SOLICITUD)
            .num_acta(UPDATED_NUM_ACTA)
            .num_acuerdo(UPDATED_NUM_ACUERDO)
            .num_notificacion(UPDATED_NUM_NOTIFICACION);

        restProcedeMockMvc.perform(put("/api/procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProcede)))
            .andExpect(status().isOk());

        // Validate the Procede in the database
        List<Procede> procedeList = procedeRepository.findAll();
        assertThat(procedeList).hasSize(databaseSizeBeforeUpdate);
        Procede testProcede = procedeList.get(procedeList.size() - 1);
        assertThat(testProcede.getActa()).isEqualTo(UPDATED_ACTA);
        assertThat(testProcede.getActaContentType()).isEqualTo(UPDATED_ACTA_CONTENT_TYPE);
        assertThat(testProcede.getAcuerdo()).isEqualTo(UPDATED_ACUERDO);
        assertThat(testProcede.getAcuerdoContentType()).isEqualTo(UPDATED_ACUERDO_CONTENT_TYPE);
        assertThat(testProcede.getId_procede()).isEqualTo(UPDATED_ID_PROCEDE);
        assertThat(testProcede.getNotificacion()).isEqualTo(UPDATED_NOTIFICACION);
        assertThat(testProcede.getNotificacionContentType()).isEqualTo(UPDATED_NOTIFICACION_CONTENT_TYPE);
        assertThat(testProcede.getId_numero_solicitud()).isEqualTo(UPDATED_ID_NUMERO_SOLICITUD);
        assertThat(testProcede.getNum_acta()).isEqualTo(UPDATED_NUM_ACTA);
        assertThat(testProcede.getNum_acuerdo()).isEqualTo(UPDATED_NUM_ACUERDO);
        assertThat(testProcede.getNum_notificacion()).isEqualTo(UPDATED_NUM_NOTIFICACION);
    }

    @Test
    public void updateNonExistingProcede() throws Exception {
        int databaseSizeBeforeUpdate = procedeRepository.findAll().size();

        // Create the Procede

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProcedeMockMvc.perform(put("/api/procedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(procede)))
            .andExpect(status().isCreated());

        // Validate the Procede in the database
        List<Procede> procedeList = procedeRepository.findAll();
        assertThat(procedeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProcede() throws Exception {
        // Initialize the database
        procedeService.save(procede);

        int databaseSizeBeforeDelete = procedeRepository.findAll().size();

        // Get the procede
        restProcedeMockMvc.perform(delete("/api/procedes/{id}", procede.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Procede> procedeList = procedeRepository.findAll();
        assertThat(procedeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Procede.class);
    }
}
