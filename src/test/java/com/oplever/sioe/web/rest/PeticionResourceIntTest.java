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

    private static final String DEFAULT_NUMERO_PETICION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PETICION = "BBBBBBBBBB";

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
            .numero_peticion(DEFAULT_NUMERO_PETICION)
            .nomsolicitante(DEFAULT_NOMSOLICITANTE)
            .paternosolicitante(DEFAULT_PATERNOSOLICITANTE)
            .maternosolicitante(DEFAULT_MATERNOSOLICITANTE)
            .cargosolicitante(DEFAULT_CARGOSOLICITANTE)
            .direccionsolicitante(DEFAULT_DIRECCIONSOLICITANTE)
            .fechayhora(DEFAULT_FECHAYHORA)
            .actocertificar(DEFAULT_ACTOCERTIFICAR)
            .oficio(DEFAULT_OFICIO)
            .oficioContentType(DEFAULT_OFICIO_CONTENT_TYPE);
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
        assertThat(testPeticion.getNumero_peticion()).isEqualTo(DEFAULT_NUMERO_PETICION);
        assertThat(testPeticion.getNomsolicitante()).isEqualTo(DEFAULT_NOMSOLICITANTE);
        assertThat(testPeticion.getPaternosolicitante()).isEqualTo(DEFAULT_PATERNOSOLICITANTE);
        assertThat(testPeticion.getMaternosolicitante()).isEqualTo(DEFAULT_MATERNOSOLICITANTE);
        assertThat(testPeticion.getCargosolicitante()).isEqualTo(DEFAULT_CARGOSOLICITANTE);
        assertThat(testPeticion.getDireccionsolicitante()).isEqualTo(DEFAULT_DIRECCIONSOLICITANTE);
        assertThat(testPeticion.getFechayhora()).isEqualTo(DEFAULT_FECHAYHORA);
        assertThat(testPeticion.getActocertificar()).isEqualTo(DEFAULT_ACTOCERTIFICAR);
        assertThat(testPeticion.getOficio()).isEqualTo(DEFAULT_OFICIO);
        assertThat(testPeticion.getOficioContentType()).isEqualTo(DEFAULT_OFICIO_CONTENT_TYPE);
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
    public void checkNumero_peticionIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setNumero_peticion(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
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
    public void getAllPeticions() throws Exception {
        // Initialize the database
        peticionRepository.save(peticion);

        // Get all the peticionList
        restPeticionMockMvc.perform(get("/api/peticions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(peticion.getId())))
            .andExpect(jsonPath("$.[*].numero_peticion").value(hasItem(DEFAULT_NUMERO_PETICION.toString())))
            .andExpect(jsonPath("$.[*].nomsolicitante").value(hasItem(DEFAULT_NOMSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].paternosolicitante").value(hasItem(DEFAULT_PATERNOSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].maternosolicitante").value(hasItem(DEFAULT_MATERNOSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].cargosolicitante").value(hasItem(DEFAULT_CARGOSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].direccionsolicitante").value(hasItem(DEFAULT_DIRECCIONSOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].fechayhora").value(hasItem(sameInstant(DEFAULT_FECHAYHORA))))
            .andExpect(jsonPath("$.[*].actocertificar").value(hasItem(DEFAULT_ACTOCERTIFICAR.toString())))
            .andExpect(jsonPath("$.[*].oficioContentType").value(hasItem(DEFAULT_OFICIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].oficio").value(hasItem(Base64Utils.encodeToString(DEFAULT_OFICIO))));
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
            .andExpect(jsonPath("$.numero_peticion").value(DEFAULT_NUMERO_PETICION.toString()))
            .andExpect(jsonPath("$.nomsolicitante").value(DEFAULT_NOMSOLICITANTE.toString()))
            .andExpect(jsonPath("$.paternosolicitante").value(DEFAULT_PATERNOSOLICITANTE.toString()))
            .andExpect(jsonPath("$.maternosolicitante").value(DEFAULT_MATERNOSOLICITANTE.toString()))
            .andExpect(jsonPath("$.cargosolicitante").value(DEFAULT_CARGOSOLICITANTE.toString()))
            .andExpect(jsonPath("$.direccionsolicitante").value(DEFAULT_DIRECCIONSOLICITANTE.toString()))
            .andExpect(jsonPath("$.fechayhora").value(sameInstant(DEFAULT_FECHAYHORA)))
            .andExpect(jsonPath("$.actocertificar").value(DEFAULT_ACTOCERTIFICAR.toString()))
            .andExpect(jsonPath("$.oficioContentType").value(DEFAULT_OFICIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.oficio").value(Base64Utils.encodeToString(DEFAULT_OFICIO)));
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
            .numero_peticion(UPDATED_NUMERO_PETICION)
            .nomsolicitante(UPDATED_NOMSOLICITANTE)
            .paternosolicitante(UPDATED_PATERNOSOLICITANTE)
            .maternosolicitante(UPDATED_MATERNOSOLICITANTE)
            .cargosolicitante(UPDATED_CARGOSOLICITANTE)
            .direccionsolicitante(UPDATED_DIRECCIONSOLICITANTE)
            .fechayhora(UPDATED_FECHAYHORA)
            .actocertificar(UPDATED_ACTOCERTIFICAR)
            .oficio(UPDATED_OFICIO)
            .oficioContentType(UPDATED_OFICIO_CONTENT_TYPE);

        restPeticionMockMvc.perform(put("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeticion)))
            .andExpect(status().isOk());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeUpdate);
        Peticion testPeticion = peticionList.get(peticionList.size() - 1);
        assertThat(testPeticion.getNumero_peticion()).isEqualTo(UPDATED_NUMERO_PETICION);
        assertThat(testPeticion.getNomsolicitante()).isEqualTo(UPDATED_NOMSOLICITANTE);
        assertThat(testPeticion.getPaternosolicitante()).isEqualTo(UPDATED_PATERNOSOLICITANTE);
        assertThat(testPeticion.getMaternosolicitante()).isEqualTo(UPDATED_MATERNOSOLICITANTE);
        assertThat(testPeticion.getCargosolicitante()).isEqualTo(UPDATED_CARGOSOLICITANTE);
        assertThat(testPeticion.getDireccionsolicitante()).isEqualTo(UPDATED_DIRECCIONSOLICITANTE);
        assertThat(testPeticion.getFechayhora()).isEqualTo(UPDATED_FECHAYHORA);
        assertThat(testPeticion.getActocertificar()).isEqualTo(UPDATED_ACTOCERTIFICAR);
        assertThat(testPeticion.getOficio()).isEqualTo(UPDATED_OFICIO);
        assertThat(testPeticion.getOficioContentType()).isEqualTo(UPDATED_OFICIO_CONTENT_TYPE);
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
