package com.jhipster.application.service;

import com.jhipster.application.service.dto.Task2DTO;
import java.util.List;

/**
 * Service Interface for managing Task2.
 */
public interface Task2Service {

    /**
     * Save a task2.
     *
     * @param task2DTO the entity to save
     * @return the persisted entity
     */
    Task2DTO save(Task2DTO task2DTO);

    /**
     * Get all the task2S.
     *
     * @return the list of entities
     */
    List<Task2DTO> findAll();

    /**
     * Get the "id" task2.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Task2DTO findOne(Long id);

    /**
     * Delete the "id" task2.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the task2 corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<Task2DTO> search(String query);
}
