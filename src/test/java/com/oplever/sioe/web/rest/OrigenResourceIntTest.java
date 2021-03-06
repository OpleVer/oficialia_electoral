package com.oplever.sioe.web.rest;

import com.oplever.sioe.OficialiaElectoralApp;

import com.oplever.sioe.domain.Origen;
import com.oplever.sioe.repository.OrigenRepository;
import com.oplever.sioe.service.OrigenService;
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
 * Test class for the OrigenResource REST controller.
 *
 * @see OrigenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OficialiaElectoralApp.class)
public class OrigenResourceIntTest {

    private static final String DEFAULT_ZONA = "AAAAAAAAAA";
    private static final String UPDATED_ZONA = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRITO = "AAAAAAAAAA";
    private static final String UPDATED_DISTRITO = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ORIGEN = "AAAAAAAAAA";
    private static final String UPDATED_ID_ORIGEN = "BBBBBBBBBB";

    @Autowired
    private OrigenRepository origenRepository;

    @Autowired
    private OrigenService origenService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restOrigenMockMvc;

    private Origen origen;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrigenResource origenResource = new OrigenResource(origenService);
        this.restOrigenMockMvc = MockMvcBuilders.standaloneSetup(origenResource)
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
    public static Origen createEntity() {
        Origen origen = new Origen()
            .zona(DEFAULT_ZONA)
            .distrito(DEFAULT_DISTRITO)
            .municipio(DEFAULT_MUNICIPIO)
            .id_origen(DEFAULT_ID_ORIGEN);
        return origen;
    }

    @Before
    public void initTest() {
        origenRepository.deleteAll();
        origen = createEntity();
    }

    @Test
    public void createOrigen() throws Exception {
        int databaseSizeBeforeCreate = origenRepository.findAll().size();

        // Create the Origen
        restOrigenMockMvc.perform(post("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origen)))
            .andExpect(status().isCreated());

        // Validate the Origen in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeCreate + 1);
        Origen testOrigen = origenList.get(origenList.size() - 1);
        assertThat(testOrigen.getZona()).isEqualTo(DEFAULT_ZONA);
        assertThat(testOrigen.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testOrigen.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
        assertThat(testOrigen.getId_origen()).isEqualTo(DEFAULT_ID_ORIGEN);
    }

    @Test
    public void createOrigenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = origenRepository.findAll().size();

        // Create the Origen with an existing ID
        origen.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrigenMockMvc.perform(post("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origen)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllOrigens() throws Exception {
        // Initialize the database
        origenRepository.save(origen);

        // Get all the origenList
        restOrigenMockMvc.perform(get("/api/origens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origen.getId())))
            .andExpect(jsonPath("$.[*].zona").value(hasItem(DEFAULT_ZONA.toString())))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO.toString())))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO.toString())))
            .andExpect(jsonPath("$.[*].id_origen").value(hasItem(DEFAULT_ID_ORIGEN.toString())));
    }

    @Test
    public void getOrigen() throws Exception {
        // Initialize the database
        origenRepository.save(origen);

        // Get the origen
        restOrigenMockMvc.perform(get("/api/origens/{id}", origen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(origen.getId()))
            .andExpect(jsonPath("$.zona").value(DEFAULT_ZONA.toString()))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO.toString()))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO.toString()))
            .andExpect(jsonPath("$.id_origen").value(DEFAULT_ID_ORIGEN.toString()));
    }

    @Test
    public void getNonExistingOrigen() throws Exception {
        // Get the origen
        restOrigenMockMvc.perform(get("/api/origens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrigen() throws Exception {
        // Initialize the database
        origenService.save(origen);

        int databaseSizeBeforeUpdate = origenRepository.findAll().size();

        // Update the origen
        Origen updatedOrigen = origenRepository.findOne(origen.getId());
        updatedOrigen
            .zona(UPDATED_ZONA)
            .distrito(UPDATED_DISTRITO)
            .municipio(UPDATED_MUNICIPIO)
            .id_origen(UPDATED_ID_ORIGEN);

        restOrigenMockMvc.perform(put("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrigen)))
            .andExpect(status().isOk());

        // Validate the Origen in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeUpdate);
        Origen testOrigen = origenList.get(origenList.size() - 1);
        assertThat(testOrigen.getZona()).isEqualTo(UPDATED_ZONA);
        assertThat(testOrigen.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testOrigen.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
        assertThat(testOrigen.getId_origen()).isEqualTo(UPDATED_ID_ORIGEN);
    }

    @Test
    public void updateNonExistingOrigen() throws Exception {
        int databaseSizeBeforeUpdate = origenRepository.findAll().size();

        // Create the Origen

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrigenMockMvc.perform(put("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origen)))
            .andExpect(status().isCreated());

        // Validate the Origen in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteOrigen() throws Exception {
        // Initialize the database
        origenService.save(origen);

        int databaseSizeBeforeDelete = origenRepository.findAll().size();

        // Get the origen
        restOrigenMockMvc.perform(delete("/api/origens/{id}", origen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Origen.class);
    }
}
