package com.jhipster.application.repository;

import com.jhipster.application.domain.Task2;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Task2 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Task2Repository extends JpaRepository<Task2, Long> {

}
