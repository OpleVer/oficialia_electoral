package com.oplever.sioe.web.rest;

import com.oplever.sioe.OficialiaElectoralApp;

import com.oplever.sioe.domain.Anexos;
import com.oplever.sioe.repository.AnexosRepository;
import com.oplever.sioe.service.AnexosService;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AnexosResource REST controller.
 *
 * @see AnexosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OficialiaElectoralApp.class)
public class AnexosResourceIntTest {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_ARCHIVO = "AAAAAAAAAA";
    private static final String UPDATED_ARCHIVO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMERO_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMERO_SOLICITUD = "BBBBBBBBBB";

    private static final String DEFAULT_ID_PROCEDE = "AAAAAAAAAA";
    private static final String UPDATED_ID_PROCEDE = "BBBBBBBBBB";

    @Autowired
    private AnexosRepository anexosRepository;

    @Autowired
    private AnexosService anexosService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAnexosMockMvc;

    private Anexos anexos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AnexosResource anexosResource = new AnexosResource(anexosService);
        this.restAnexosMockMvc = MockMvcBuilders.standaloneSetup(anexosResource)
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
    public static Anexos createEntity() {
        Anexos anexos = new Anexos()
            .descripcion(DEFAULT_DESCRIPCION)
            .archivo(DEFAULT_ARCHIVO)
            .id_numero_solicitud(DEFAULT_ID_NUMERO_SOLICITUD)
            .id_procede(DEFAULT_ID_PROCEDE);
        return anexos;
    }

    @Before
    public void initTest() {
        anexosRepository.deleteAll();
        anexos = createEntity();
    }

    @Test
    public void createAnexos() throws Exception {
        int databaseSizeBeforeCreate = anexosRepository.findAll().size();

        // Create the Anexos
        restAnexosMockMvc.perform(post("/api/anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexos)))
            .andExpect(status().isCreated());

        // Validate the Anexos in the database
        List<Anexos> anexosList = anexosRepository.findAll();
        assertThat(anexosList).hasSize(databaseSizeBeforeCreate + 1);
        Anexos testAnexos = anexosList.get(anexosList.size() - 1);
        assertThat(testAnexos.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testAnexos.getArchivo()).isEqualTo(DEFAULT_ARCHIVO);
        assertThat(testAnexos.getId_numero_solicitud()).isEqualTo(DEFAULT_ID_NUMERO_SOLICITUD);
        assertThat(testAnexos.getId_procede()).isEqualTo(DEFAULT_ID_PROCEDE);
    }

    @Test
    public void createAnexosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anexosRepository.findAll().size();

        // Create the Anexos with an existing ID
        anexos.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnexosMockMvc.perform(post("/api/anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexos)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Anexos> anexosList = anexosRepository.findAll();
        assertThat(anexosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAnexos() throws Exception {
        // Initialize the database
        anexosRepository.save(anexos);

        // Get all the anexosList
        restAnexosMockMvc.perform(get("/api/anexos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anexos.getId())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].archivo").value(hasItem(DEFAULT_ARCHIVO.toString())))
            .andExpect(jsonPath("$.[*].id_numero_solicitud").value(hasItem(DEFAULT_ID_NUMERO_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].id_procede").value(hasItem(DEFAULT_ID_PROCEDE.toString())));
    }

    @Test
    public void getAnexos() throws Exception {
        // Initialize the database
        anexosRepository.save(anexos);

        // Get the anexos
        restAnexosMockMvc.perform(get("/api/anexos/{id}", anexos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(anexos.getId()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.archivo").value(DEFAULT_ARCHIVO.toString()))
            .andExpect(jsonPath("$.id_numero_solicitud").value(DEFAULT_ID_NUMERO_SOLICITUD.toString()))
            .andExpect(jsonPath("$.id_procede").value(DEFAULT_ID_PROCEDE.toString()));
    }

    @Test
    public void getNonExistingAnexos() throws Exception {
        // Get the anexos
        restAnexosMockMvc.perform(get("/api/anexos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAnexos() throws Exception {
        // Initialize the database
        anexosService.save(anexos);

        int databaseSizeBeforeUpdate = anexosRepository.findAll().size();

        // Update the anexos
        Anexos updatedAnexos = anexosRepository.findOne(anexos.getId());
        updatedAnexos
            .descripcion(UPDATED_DESCRIPCION)
            .archivo(UPDATED_ARCHIVO)
            .id_numero_solicitud(UPDATED_ID_NUMERO_SOLICITUD)
            .id_procede(UPDATED_ID_PROCEDE);

        restAnexosMockMvc.perform(put("/api/anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAnexos)))
            .andExpect(status().isOk());

        // Validate the Anexos in the database
        List<Anexos> anexosList = anexosRepository.findAll();
        assertThat(anexosList).hasSize(databaseSizeBeforeUpdate);
        Anexos testAnexos = anexosList.get(anexosList.size() - 1);
        assertThat(testAnexos.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testAnexos.getArchivo()).isEqualTo(UPDATED_ARCHIVO);
        assertThat(testAnexos.getId_numero_solicitud()).isEqualTo(UPDATED_ID_NUMERO_SOLICITUD);
        assertThat(testAnexos.getId_procede()).isEqualTo(UPDATED_ID_PROCEDE);
    }

    @Test
    public void updateNonExistingAnexos() throws Exception {
        int databaseSizeBeforeUpdate = anexosRepository.findAll().size();

        // Create the Anexos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAnexosMockMvc.perform(put("/api/anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anexos)))
            .andExpect(status().isCreated());

        // Validate the Anexos in the database
        List<Anexos> anexosList = anexosRepository.findAll();
        assertThat(anexosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAnexos() throws Exception {
        // Initialize the database
        anexosService.save(anexos);

        int databaseSizeBeforeDelete = anexosRepository.findAll().size();

        // Get the anexos
        restAnexosMockMvc.perform(delete("/api/anexos/{id}", anexos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Anexos> anexosList = anexosRepository.findAll();
        assertThat(anexosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Anexos.class);
    }
}
