package com.jhipster.application.web.rest;

import com.jhipster.application.JhipsterSampleApplicationApp;

import com.jhipster.application.domain.Task2;
import com.jhipster.application.repository.Task2Repository;
import com.jhipster.application.service.Task2Service;
import com.jhipster.application.repository.search.Task2SearchRepository;
import com.jhipster.application.service.dto.Task2DTO;
import com.jhipster.application.service.mapper.Task2Mapper;
import com.jhipster.application.web.rest.errors.ExceptionTranslator;

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

import static com.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Task2Resource REST controller.
 *
 * @see Task2Resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class Task2ResourceIntTest {

    private static final Integer DEFAULT_APP_ID = 1;
    private static final Integer UPDATED_APP_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCE = "AAAAAAAAAA";
    private static final String UPDATED_DESCE = "BBBBBBBBBB";

    private static final String DEFAULT_WX_APPID = "AAAAAAAAAA";
    private static final String UPDATED_WX_APPID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private Task2Repository task2Repository;

    @Autowired
    private Task2Mapper task2Mapper;

    @Autowired
    private Task2Service task2Service;

    @Autowired
    private Task2SearchRepository task2SearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTask2MockMvc;

    private Task2 task2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Task2Resource task2Resource = new Task2Resource(task2Service);
        this.restTask2MockMvc = MockMvcBuilders.standaloneSetup(task2Resource)
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
    public static Task2 createEntity(EntityManager em) {
        Task2 task2 = new Task2()
            .appId(DEFAULT_APP_ID)
            .name(DEFAULT_NAME)
            .desce(DEFAULT_DESCE)
            .wxAppid(DEFAULT_WX_APPID)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION);
        return task2;
    }

    @Before
    public void initTest() {
        task2SearchRepository.deleteAll();
        task2 = createEntity(em);
    }

    @Test
    @Transactional
    public void createTask2() throws Exception {
        int databaseSizeBeforeCreate = task2Repository.findAll().size();

        // Create the Task2
        Task2DTO task2DTO = task2Mapper.toDto(task2);
        restTask2MockMvc.perform(post("/api/task-2-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(task2DTO)))
            .andExpect(status().isCreated());

        // Validate the Task2 in the database
        List<Task2> task2List = task2Repository.findAll();
        assertThat(task2List).hasSize(databaseSizeBeforeCreate + 1);
        Task2 testTask2 = task2List.get(task2List.size() - 1);
        assertThat(testTask2.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testTask2.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTask2.getDesce()).isEqualTo(DEFAULT_DESCE);
        assertThat(testTask2.getWxAppid()).isEqualTo(DEFAULT_WX_APPID);
        assertThat(testTask2.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTask2.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);

        // Validate the Task2 in Elasticsearch
        Task2 task2Es = task2SearchRepository.findOne(testTask2.getId());
        assertThat(task2Es).isEqualToIgnoringGivenFields(testTask2);
    }

    @Test
    @Transactional
    public void createTask2WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = task2Repository.findAll().size();

        // Create the Task2 with an existing ID
        task2.setId(1L);
        Task2DTO task2DTO = task2Mapper.toDto(task2);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTask2MockMvc.perform(post("/api/task-2-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(task2DTO)))
            .andExpect(status().isBadRequest());

        // Validate the Task2 in the database
        List<Task2> task2List = task2Repository.findAll();
        assertThat(task2List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTask2S() throws Exception {
        // Initialize the database
        task2Repository.saveAndFlush(task2);

        // Get all the task2List
        restTask2MockMvc.perform(get("/api/task-2-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(task2.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].desce").value(hasItem(DEFAULT_DESCE.toString())))
            .andExpect(jsonPath("$.[*].wxAppid").value(hasItem(DEFAULT_WX_APPID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getTask2() throws Exception {
        // Initialize the database
        task2Repository.saveAndFlush(task2);

        // Get the task2
        restTask2MockMvc.perform(get("/api/task-2-s/{id}", task2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(task2.getId().intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.desce").value(DEFAULT_DESCE.toString()))
            .andExpect(jsonPath("$.wxAppid").value(DEFAULT_WX_APPID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTask2() throws Exception {
        // Get the task2
        restTask2MockMvc.perform(get("/api/task-2-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTask2() throws Exception {
        // Initialize the database
        task2Repository.saveAndFlush(task2);
        task2SearchRepository.save(task2);
        int databaseSizeBeforeUpdate = task2Repository.findAll().size();

        // Update the task2
        Task2 updatedTask2 = task2Repository.findOne(task2.getId());
        // Disconnect from session so that the updates on updatedTask2 are not directly saved in db
        em.detach(updatedTask2);
        updatedTask2
            .appId(UPDATED_APP_ID)
            .name(UPDATED_NAME)
            .desce(UPDATED_DESCE)
            .wxAppid(UPDATED_WX_APPID)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);
        Task2DTO task2DTO = task2Mapper.toDto(updatedTask2);

        restTask2MockMvc.perform(put("/api/task-2-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(task2DTO)))
            .andExpect(status().isOk());

        // Validate the Task2 in the database
        List<Task2> task2List = task2Repository.findAll();
        assertThat(task2List).hasSize(databaseSizeBeforeUpdate);
        Task2 testTask2 = task2List.get(task2List.size() - 1);
        assertThat(testTask2.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testTask2.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTask2.getDesce()).isEqualTo(UPDATED_DESCE);
        assertThat(testTask2.getWxAppid()).isEqualTo(UPDATED_WX_APPID);
        assertThat(testTask2.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTask2.getDescription()).isEqualTo(UPDATED_DESCRIPTION);

        // Validate the Task2 in Elasticsearch
        Task2 task2Es = task2SearchRepository.findOne(testTask2.getId());
        assertThat(task2Es).isEqualToIgnoringGivenFields(testTask2);
    }

    @Test
    @Transactional
    public void updateNonExistingTask2() throws Exception {
        int databaseSizeBeforeUpdate = task2Repository.findAll().size();

        // Create the Task2
        Task2DTO task2DTO = task2Mapper.toDto(task2);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTask2MockMvc.perform(put("/api/task-2-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(task2DTO)))
            .andExpect(status().isCreated());

        // Validate the Task2 in the database
        List<Task2> task2List = task2Repository.findAll();
        assertThat(task2List).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTask2() throws Exception {
        // Initialize the database
        task2Repository.saveAndFlush(task2);
        task2SearchRepository.save(task2);
        int databaseSizeBeforeDelete = task2Repository.findAll().size();

        // Get the task2
        restTask2MockMvc.perform(delete("/api/task-2-s/{id}", task2.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean task2ExistsInEs = task2SearchRepository.exists(task2.getId());
        assertThat(task2ExistsInEs).isFalse();

        // Validate the database is empty
        List<Task2> task2List = task2Repository.findAll();
        assertThat(task2List).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchTask2() throws Exception {
        // Initialize the database
        task2Repository.saveAndFlush(task2);
        task2SearchRepository.save(task2);

        // Search the task2
        restTask2MockMvc.perform(get("/api/_search/task-2-s?query=id:" + task2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(task2.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].desce").value(hasItem(DEFAULT_DESCE.toString())))
            .andExpect(jsonPath("$.[*].wxAppid").value(hasItem(DEFAULT_WX_APPID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Task2.class);
        Task2 task21 = new Task2();
        task21.setId(1L);
        Task2 task22 = new Task2();
        task22.setId(task21.getId());
        assertThat(task21).isEqualTo(task22);
        task22.setId(2L);
        assertThat(task21).isNotEqualTo(task22);
        task21.setId(null);
        assertThat(task21).isNotEqualTo(task22);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Task2DTO.class);
        Task2DTO task2DTO1 = new Task2DTO();
        task2DTO1.setId(1L);
        Task2DTO task2DTO2 = new Task2DTO();
        assertThat(task2DTO1).isNotEqualTo(task2DTO2);
        task2DTO2.setId(task2DTO1.getId());
        assertThat(task2DTO1).isEqualTo(task2DTO2);
        task2DTO2.setId(2L);
        assertThat(task2DTO1).isNotEqualTo(task2DTO2);
        task2DTO1.setId(null);
        assertThat(task2DTO1).isNotEqualTo(task2DTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(task2Mapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(task2Mapper.fromId(null)).isNull();
    }
}
