package com.oplever.sioe.web.rest;

import com.oplever.sioe.OficialiaElectoralApp;

import com.oplever.sioe.domain.Peticion;
import com.oplever.sioe.repository.PeticionRepository;
import com.oplever.sioe.service.PeticionService;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.oplever.sioe.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PeticionResource REST controller.
 *
 * @see PeticionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OficialiaElectoralApp.class)
public class PeticionResourceIntTest {

    private static final String DEFAULT_NOMSOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOMSOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_PATERNOSOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_PATERNOSOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_MATERNOSOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_MATERNOSOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_CARGOSOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_CARGOSOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCIONSOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCIONSOLICITANTE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FECHAYHORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHAYHORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ACTOCERTIFICAR = "AAAAAAAAAA";
    private static final String UPDATED_ACTOCERTIFICAR = "BBBBBBBBBB";

    private static final byte[] DEFAULT_OFICIO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_OFICIO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_OFICIO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_OFICIO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NOM_REPS_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_NOM_REPS_SOLICITUD = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMERO_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMERO_SOLICITUD = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ORIGEN = "AAAAAAAAAA";
    private static final String UPDATED_ID_ORIGEN = "BBBBBBBBBB";

    private static final byte[] DEFAULT_OFICIO_PREVENCION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_OFICIO_PREVENCION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_OFICIO_PREVENCION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NUM_OFICIO_PREVENCION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_OFICIO_PREVENCION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_NOTIFICACION_PREVENCION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION_PREVENCION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_PREVENCION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NUM_NOTIFICACION_PREVENCION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_NOTIFICACION_PREVENCION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_RESPUESTA_PREVENCION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RESPUESTA_PREVENCION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RESPUESTA_PREVENCION_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NUM_RESPUESTA_PREVENCION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_RESPUESTA_PREVENCION = "BBBBBBBBBB";

    @Autowired
    private PeticionRepository peticionRepository;

    @Autowired
    private PeticionService peticionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restPeticionMockMvc;

    private Peticion peticion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PeticionResource peticionResource = new PeticionResource(peticionService);
        this.restPeticionMockMvc = MockMvcBuilders.standaloneSetup(peticionResource)
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
    public static Peticion createEntity() {
        Peticion peticion = new Peticion()
            .nomsolicitante(DEFAULT_NOMSOLICITANTE)
            .paternosolicitante(DEFAULT_PATERNOSOLICITANTE)
            .maternosolicitante(DEFAULT_MATERNOSOLICITANTE)
            .cargosolicitante(DEFAULT_CARGOSOLICITANTE)
            .direccionsolicitante(DEFAULT_DIRECCIONSOLICITANTE)
            .fechayhora(DEFAULT_FECHAYHORA)
            .actocertificar(DEFAULT_ACTOCERTIFICAR)
            .oficio(DEFAULT_OFICIO)
            .oficioContentType(DEFAULT_OFICIO_CONTENT_TYPE)
            .nom_reps_solicitud(DEFAULT_NOM_REPS_SOLICITUD)
            .id_numero_solicitud(DEFAULT_ID_NUMERO_SOLICITUD)
            .id_origen(DEFAULT_ID_ORIGEN)
            .oficio_prevencion(DEFAULT_OFICIO_PREVENCION)
            .oficio_prevencionContentType(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE)
            .num_oficio_prevencion(DEFAULT_NUM_OFICIO_PREVENCION)
            .notificacion_prevencion(DEFAULT_NOTIFICACION_PREVENCION)
            .notificacion_prevencionContentType(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE)
            .num_notificacion_prevencion(DEFAULT_NUM_NOTIFICACION_PREVENCION)
            .respuesta_prevencion(DEFAULT_RESPUESTA_PREVENCION)
            .respuesta_prevencionContentType(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE)
            .num_respuesta_prevencion(DEFAULT_NUM_RESPUESTA_PREVENCION);
        return peticion;
    }

    @Before
    public void initTest() {
        peticionRepository.deleteAll();
        peticion = createEntity();
    }

    @Test
    public void createPeticion() throws Exception {
        int databaseSizeBeforeCreate = peticionRepository.findAll().size();

        // Create the Peticion
        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isCreated());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeCreate + 1);
        Peticion testPeticion = peticionList.get(peticionList.size() - 1);
        assertThat(testPeticion.getNomsolicitante()).isEqualTo(DEFAULT_NOMSOLICITANTE);
        assertThat(testPeticion.getPaternosolicitante()).isEqualTo(DEFAULT_PATERNOSOLICITANTE);
        assertThat(testPeticion.getMaternosolicitante()).isEqualTo(DEFAULT_MATERNOSOLICITANTE);
        assertThat(testPeticion.getCargosolicitante()).isEqualTo(DEFAULT_CARGOSOLICITANTE);
        assertThat(testPeticion.getDireccionsolicitante()).isEqualTo(DEFAULT_DIRECCIONSOLICITANTE);
        assertThat(testPeticion.getFechayhora()).isEqualTo(DEFAULT_FECHAYHORA);
        assertThat(testPeticion.getActocertificar()).isEqualTo(DEFAULT_ACTOCERTIFICAR);
        assertThat(testPeticion.getOficio()).isEqualTo(DEFAULT_OFICIO);
        assertThat(testPeticion.getOficioContentType()).isEqualTo(DEFAULT_OFICIO_CONTENT_TYPE);
        assertThat(testPeticion.getNom_reps_solicitud()).isEqualTo(DEFAULT_NOM_REPS_SOLICITUD);
        assertThat(testPeticion.getId_numero_solicitud()).isEqualTo(DEFAULT_ID_NUMERO_SOLICITUD);
        assertThat(testPeticion.getId_origen()).isEqualTo(DEFAULT_ID_ORIGEN);
        assertThat(testPeticion.getOficio_prevencion()).isEqualTo(DEFAULT_OFICIO_PREVENCION);
        assertThat(testPeticion.getOficio_prevencionContentType()).isEqualTo(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNum_oficio_prevencion()).isEqualTo(DEFAULT_NUM_OFICIO_PREVENCION);
        assertThat(testPeticion.getNotificacion_prevencion()).isEqualTo(DEFAULT_NOTIFICACION_PREVENCION);
        assertThat(testPeticion.getNotificacion_prevencionContentType()).isEqualTo(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNum_notificacion_prevencion()).isEqualTo(DEFAULT_NUM_NOTIFICACION_PREVENCION);
        assertThat(testPeticion.getRespuesta_prevencion()).isEqualTo(DEFAULT_RESPUESTA_PREVENCION);
        assertThat(testPeticion.getRespuesta_prevencionContentType()).isEqualTo(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNum_respuesta_prevencion()).isEqualTo(DEFAULT_NUM_RESPUESTA_PREVENCION);
    }

    @Test
    public void createPeticionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = peticionRepository.findAll().size();

        // Create the Peticion with an existing ID
        peticion.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNomsolicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setNomsolicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPaternosolicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setPaternosolicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMaternosolicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setMaternosolicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFechayhoraIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setFechayhora(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkActocertificarIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setActocertificar(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkId_numero_solicitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setId_numero_solicitud(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllPeticions() throws Exception {
        // Initialize the database
        peticionRepository.save(peticion);

        // Get all the peticionList
        restPeticionMockMvc.perform(get("/api/peticions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(peticion.getId())))
            .andExpect(jsonPath("$.[*].nomsolicitante").value(hasItem(DEFAULT_NOMSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].paternosolicitante").value(hasItem(DEFAULT_PATERNOSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].maternosolicitante").value(hasItem(DEFAULT_MATERNOSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].cargosolicitante").value(hasItem(DEFAULT_CARGOSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].direccionsolicitante").value(hasItem(DEFAULT_DIRECCIONSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].fechayhora").value(hasItem(sameInstant(DEFAULT_FECHAYHORA))))
            .andExpect(jsonPath("$.[*].actocertificar").value(hasItem(DEFAULT_ACTOCERTIFICAR.toString())))
            .andExpect(jsonPath("$.[*].oficioContentType").value(hasItem(DEFAULT_OFICIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].oficio").value(hasItem(Base64Utils.encodeToString(DEFAULT_OFICIO))))
            .andExpect(jsonPath("$.[*].nom_reps_solicitud").value(hasItem(DEFAULT_NOM_REPS_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].id_numero_solicitud").value(hasItem(DEFAULT_ID_NUMERO_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].id_origen").value(hasItem(DEFAULT_ID_ORIGEN.toString())))
            .andExpect(jsonPath("$.[*].oficio_prevencionContentType").value(hasItem(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].oficio_prevencion").value(hasItem(Base64Utils.encodeToString(DEFAULT_OFICIO_PREVENCION))))
            .andExpect(jsonPath("$.[*].num_oficio_prevencion").value(hasItem(DEFAULT_NUM_OFICIO_PREVENCION.toString())))
            .andExpect(jsonPath("$.[*].notificacion_prevencionContentType").value(hasItem(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion_prevencion").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PREVENCION))))
            .andExpect(jsonPath("$.[*].num_notificacion_prevencion").value(hasItem(DEFAULT_NUM_NOTIFICACION_PREVENCION.toString())))
            .andExpect(jsonPath("$.[*].respuesta_prevencionContentType").value(hasItem(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].respuesta_prevencion").value(hasItem(Base64Utils.encodeToString(DEFAULT_RESPUESTA_PREVENCION))))
            .andExpect(jsonPath("$.[*].num_respuesta_prevencion").value(hasItem(DEFAULT_NUM_RESPUESTA_PREVENCION.toString())));
    }

    @Test
    public void getPeticion() throws Exception {
        // Initialize the database
        peticionRepository.save(peticion);

        // Get the peticion
        restPeticionMockMvc.perform(get("/api/peticions/{id}", peticion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(peticion.getId()))
            .andExpect(jsonPath("$.nomsolicitante").value(DEFAULT_NOMSOLICITANTE.toString()))
            .andExpect(jsonPath("$.paternosolicitante").value(DEFAULT_PATERNOSOLICITANTE.toString()))
            .andExpect(jsonPath("$.maternosolicitante").value(DEFAULT_MATERNOSOLICITANTE.toString()))
            .andExpect(jsonPath("$.cargosolicitante").value(DEFAULT_CARGOSOLICITANTE.toString()))
            .andExpect(jsonPath("$.direccionsolicitante").value(DEFAULT_DIRECCIONSOLICITANTE.toString()))
            .andExpect(jsonPath("$.fechayhora").value(sameInstant(DEFAULT_FECHAYHORA)))
            .andExpect(jsonPath("$.actocertificar").value(DEFAULT_ACTOCERTIFICAR.toString()))
            .andExpect(jsonPath("$.oficioContentType").value(DEFAULT_OFICIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.oficio").value(Base64Utils.encodeToString(DEFAULT_OFICIO)))
            .andExpect(jsonPath("$.nom_reps_solicitud").value(DEFAULT_NOM_REPS_SOLICITUD.toString()))
            .andExpect(jsonPath("$.id_numero_solicitud").value(DEFAULT_ID_NUMERO_SOLICITUD.toString()))
            .andExpect(jsonPath("$.id_origen").value(DEFAULT_ID_ORIGEN.toString()))
            .andExpect(jsonPath("$.oficio_prevencionContentType").value(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE))
            .andExpect(jsonPath("$.oficio_prevencion").value(Base64Utils.encodeToString(DEFAULT_OFICIO_PREVENCION)))
            .andExpect(jsonPath("$.num_oficio_prevencion").value(DEFAULT_NUM_OFICIO_PREVENCION.toString()))
            .andExpect(jsonPath("$.notificacion_prevencionContentType").value(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion_prevencion").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PREVENCION)))
            .andExpect(jsonPath("$.num_notificacion_prevencion").value(DEFAULT_NUM_NOTIFICACION_PREVENCION.toString()))
            .andExpect(jsonPath("$.respuesta_prevencionContentType").value(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE))
            .andExpect(jsonPath("$.respuesta_prevencion").value(Base64Utils.encodeToString(DEFAULT_RESPUESTA_PREVENCION)))
            .andExpect(jsonPath("$.num_respuesta_prevencion").value(DEFAULT_NUM_RESPUESTA_PREVENCION.toString()));
    }

    @Test
    public void getNonExistingPeticion() throws Exception {
        // Get the peticion
        restPeticionMockMvc.perform(get("/api/peticions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePeticion() throws Exception {
        // Initialize the database
        peticionService.save(peticion);

        int databaseSizeBeforeUpdate = peticionRepository.findAll().size();

        // Update the peticion
        Peticion updatedPeticion = peticionRepository.findOne(peticion.getId());
        updatedPeticion
            .nomsolicitante(UPDATED_NOMSOLICITANTE)
            .paternosolicitante(UPDATED_PATERNOSOLICITANTE)
            .maternosolicitante(UPDATED_MATERNOSOLICITANTE)
            .cargosolicitante(UPDATED_CARGOSOLICITANTE)
            .direccionsolicitante(UPDATED_DIRECCIONSOLICITANTE)
            .fechayhora(UPDATED_FECHAYHORA)
            .actocertificar(UPDATED_ACTOCERTIFICAR)
            .oficio(UPDATED_OFICIO)
            .oficioContentType(UPDATED_OFICIO_CONTENT_TYPE)
            .nom_reps_solicitud(UPDATED_NOM_REPS_SOLICITUD)
            .id_numero_solicitud(UPDATED_ID_NUMERO_SOLICITUD)
            .id_origen(UPDATED_ID_ORIGEN)
            .oficio_prevencion(UPDATED_OFICIO_PREVENCION)
            .oficio_prevencionContentType(UPDATED_OFICIO_PREVENCION_CONTENT_TYPE)
            .num_oficio_prevencion(UPDATED_NUM_OFICIO_PREVENCION)
            .notificacion_prevencion(UPDATED_NOTIFICACION_PREVENCION)
            .notificacion_prevencionContentType(UPDATED_NOTIFICACION_PREVENCION_CONTENT_TYPE)
            .num_notificacion_prevencion(UPDATED_NUM_NOTIFICACION_PREVENCION)
            .respuesta_prevencion(UPDATED_RESPUESTA_PREVENCION)
            .respuesta_prevencionContentType(UPDATED_RESPUESTA_PREVENCION_CONTENT_TYPE)
            .num_respuesta_prevencion(UPDATED_NUM_RESPUESTA_PREVENCION);

        restPeticionMockMvc.perform(put("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeticion)))
            .andExpect(status().isOk());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeUpdate);
        Peticion testPeticion = peticionList.get(peticionList.size() - 1);
        assertThat(testPeticion.getNomsolicitante()).isEqualTo(UPDATED_NOMSOLICITANTE);
        assertThat(testPeticion.getPaternosolicitante()).isEqualTo(UPDATED_PATERNOSOLICITANTE);
        assertThat(testPeticion.getMaternosolicitante()).isEqualTo(UPDATED_MATERNOSOLICITANTE);
        assertThat(testPeticion.getCargosolicitante()).isEqualTo(UPDATED_CARGOSOLICITANTE);
        assertThat(testPeticion.getDireccionsolicitante()).isEqualTo(UPDATED_DIRECCIONSOLICITANTE);
        assertThat(testPeticion.getFechayhora()).isEqualTo(UPDATED_FECHAYHORA);
        assertThat(testPeticion.getActocertificar()).isEqualTo(UPDATED_ACTOCERTIFICAR);
        assertThat(testPeticion.getOficio()).isEqualTo(UPDATED_OFICIO);
        assertThat(testPeticion.getOficioContentType()).isEqualTo(UPDATED_OFICIO_CONTENT_TYPE);
        assertThat(testPeticion.getNom_reps_solicitud()).isEqualTo(UPDATED_NOM_REPS_SOLICITUD);
        assertThat(testPeticion.getId_numero_solicitud()).isEqualTo(UPDATED_ID_NUMERO_SOLICITUD);
        assertThat(testPeticion.getId_origen()).isEqualTo(UPDATED_ID_ORIGEN);
        assertThat(testPeticion.getOficio_prevencion()).isEqualTo(UPDATED_OFICIO_PREVENCION);
        assertThat(testPeticion.getOficio_prevencionContentType()).isEqualTo(UPDATED_OFICIO_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNum_oficio_prevencion()).isEqualTo(UPDATED_NUM_OFICIO_PREVENCION);
        assertThat(testPeticion.getNotificacion_prevencion()).isEqualTo(UPDATED_NOTIFICACION_PREVENCION);
        assertThat(testPeticion.getNotificacion_prevencionContentType()).isEqualTo(UPDATED_NOTIFICACION_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNum_notificacion_prevencion()).isEqualTo(UPDATED_NUM_NOTIFICACION_PREVENCION);
        assertThat(testPeticion.getRespuesta_prevencion()).isEqualTo(UPDATED_RESPUESTA_PREVENCION);
        assertThat(testPeticion.getRespuesta_prevencionContentType()).isEqualTo(UPDATED_RESPUESTA_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNum_respuesta_prevencion()).isEqualTo(UPDATED_NUM_RESPUESTA_PREVENCION);
    }

    @Test
    public void updateNonExistingPeticion() throws Exception {
        int databaseSizeBeforeUpdate = peticionRepository.findAll().size();

        // Create the Peticion

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPeticionMockMvc.perform(put("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isCreated());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deletePeticion() throws Exception {
        // Initialize the database
        peticionService.save(peticion);

        int databaseSizeBeforeDelete = peticionRepository.findAll().size();

        // Get the peticion
        restPeticionMockMvc.perform(delete("/api/peticions/{id}", peticion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Peticion.class);
    }
}
