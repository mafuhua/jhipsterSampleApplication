package com.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jhipster.application.service.Task2Service;
import com.jhipster.application.web.rest.errors.BadRequestAlertException;
import com.jhipster.application.web.rest.util.HeaderUtil;
import com.jhipster.application.service.dto.Task2DTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Task2.
 */
@RestController
@RequestMapping("/api")
public class Task2Resource {

    private final Logger log = LoggerFactory.getLogger(Task2Resource.class);

    private static final String ENTITY_NAME = "task2";

    private final Task2Service task2Service;

    public Task2Resource(Task2Service task2Service) {
        this.task2Service = task2Service;
    }

    /**
     * POST  /task-2-s : Create a new task2.
     *
     * @param task2DTO the task2DTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new task2DTO, or with status 400 (Bad Request) if the task2 has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/task-2-s")
    @Timed
    public ResponseEntity<Task2DTO> createTask2(@RequestBody Task2DTO task2DTO) throws URISyntaxException {
        log.debug("REST request to save Task2 : {}", task2DTO);
        if (task2DTO.getId() != null) {
            throw new BadRequestAlertException("A new task2 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Task2DTO result = task2Service.save(task2DTO);
        return ResponseEntity.created(new URI("/api/task-2-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /task-2-s : Updates an existing task2.
     *
     * @param task2DTO the task2DTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated task2DTO,
     * or with status 400 (Bad Request) if the task2DTO is not valid,
     * or with status 500 (Internal Server Error) if the task2DTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/task-2-s")
    @Timed
    public ResponseEntity<Task2DTO> updateTask2(@RequestBody Task2DTO task2DTO) throws URISyntaxException {
        log.debug("REST request to update Task2 : {}", task2DTO);
        if (task2DTO.getId() == null) {
            return createTask2(task2DTO);
        }
        Task2DTO result = task2Service.save(task2DTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, task2DTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /task-2-s : get all the task2S.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of task2S in body
     */
    @GetMapping("/task-2-s")
    @Timed
    public List<Task2DTO> getAllTask2S() {
        log.debug("REST request to get all Task2S");
        return task2Service.findAll();
        }

    /**
     * GET  /task-2-s/:id : get the "id" task2.
     *
     * @param id the id of the task2DTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the task2DTO, or with status 404 (Not Found)
     */
    @GetMapping("/task-2-s/{id}")
    @Timed
    public ResponseEntity<Task2DTO> getTask2(@PathVariable Long id) {
        log.debug("REST request to get Task2 : {}", id);
        Task2DTO task2DTO = task2Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(task2DTO));
    }

    /**
     * DELETE  /task-2-s/:id : delete the "id" task2.
     *
     * @param id the id of the task2DTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/task-2-s/{id}")
    @Timed
    public ResponseEntity<Void> deleteTask2(@PathVariable Long id) {
        log.debug("REST request to delete Task2 : {}", id);
        task2Service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/task-2-s?query=:query : search for the task2 corresponding
     * to the query.
     *
     * @param query the query of the task2 search
     * @return the result of the search
     */
    @GetMapping("/_search/task-2-s")
    @Timed
    public List<Task2DTO> searchTask2S(@RequestParam String query) {
        log.debug("REST request to search Task2S for query {}", query);
        return task2Service.search(query);
    }

}
