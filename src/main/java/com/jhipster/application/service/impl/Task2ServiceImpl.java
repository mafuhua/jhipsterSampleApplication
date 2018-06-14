package com.jhipster.application.service.impl;

import com.jhipster.application.service.Task2Service;
import com.jhipster.application.domain.Task2;
import com.jhipster.application.repository.Task2Repository;
import com.jhipster.application.repository.search.Task2SearchRepository;
import com.jhipster.application.service.dto.Task2DTO;
import com.jhipster.application.service.mapper.Task2Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Task2.
 */
@Service
@Transactional
public class Task2ServiceImpl implements Task2Service {

    private final Logger log = LoggerFactory.getLogger(Task2ServiceImpl.class);

    private final Task2Repository task2Repository;

    private final Task2Mapper task2Mapper;

    private final Task2SearchRepository task2SearchRepository;

    public Task2ServiceImpl(Task2Repository task2Repository, Task2Mapper task2Mapper, Task2SearchRepository task2SearchRepository) {
        this.task2Repository = task2Repository;
        this.task2Mapper = task2Mapper;
        this.task2SearchRepository = task2SearchRepository;
    }

    /**
     * Save a task2.
     *
     * @param task2DTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Task2DTO save(Task2DTO task2DTO) {
        log.debug("Request to save Task2 : {}", task2DTO);
        Task2 task2 = task2Mapper.toEntity(task2DTO);
        task2 = task2Repository.save(task2);
        Task2DTO result = task2Mapper.toDto(task2);
        task2SearchRepository.save(task2);
        return result;
    }

    /**
     * Get all the task2S.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task2DTO> findAll() {
        log.debug("Request to get all Task2S");
        return task2Repository.findAll().stream()
            .map(task2Mapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one task2 by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Task2DTO findOne(Long id) {
        log.debug("Request to get Task2 : {}", id);
        Task2 task2 = task2Repository.findOne(id);
        return task2Mapper.toDto(task2);
    }

    /**
     * Delete the task2 by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Task2 : {}", id);
        task2Repository.delete(id);
        task2SearchRepository.delete(id);
    }

    /**
     * Search for the task2 corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task2DTO> search(String query) {
        log.debug("Request to search Task2S for query {}", query);
        return StreamSupport
            .stream(task2SearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(task2Mapper::toDto)
            .collect(Collectors.toList());
    }
}
